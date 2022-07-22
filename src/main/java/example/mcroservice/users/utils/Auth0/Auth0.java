package example.mcroservice.users.utils.Auth0;

import java.security.interfaces.RSAPublicKey;

import com.auth0.client.auth.AuthAPI;
import com.auth0.client.mgmt.ManagementAPI;
import com.auth0.exception.APIException;
import com.auth0.exception.Auth0Exception;
import com.auth0.exception.IdTokenValidationException;
import com.auth0.exception.PublicKeyProviderException;
import com.auth0.json.auth.CreatedUser;
import com.auth0.json.auth.TokenHolder;
import com.auth0.json.mgmt.users.User;
import com.auth0.jwk.JwkException;
import com.auth0.jwk.JwkProvider;
import com.auth0.jwk.JwkProviderBuilder;
import com.auth0.net.AuthRequest;
import com.auth0.net.Request;
import com.auth0.net.SignUpRequest;
import com.auth0.utils.tokens.IdTokenVerifier;
import com.auth0.utils.tokens.PublicKeyProvider;
import com.auth0.utils.tokens.SignatureVerifier;

import org.springframework.stereotype.Component;

import example.mcroservice.users.errors.my_exceptions.My_api_exception;
import example.mcroservice.users.errors.my_exceptions.My_auth0_exception;

@Component
public class Auth0 {
    // Propiedades que manejan los datos sencibles
    // Aun no se como sacarlas como env
    private String api_domain = "dev-51lqhc63.us.auth0.com";
    private String client_id = "mQIv59sxBT4kdURqMHOjkbN8QpPKlWPG";
    private String client_secret = "bUrRwImhYusIyoazpDZ-bmtCaTLjWbfaYk8jANEs9g2g383qb3crPGZwLebR5CfW";
    private String connection_type = "Username-Password-Authentication";

    // EL SDK-------------------------------------------------------------------
    private AuthAPI auth_api = new AuthAPI(
            api_domain, // Api domain
            client_id, // Api cliend Id
            client_secret // Api cliend secret id
    );
    // EL API MANAGEMENT--------------------------------------------------------
    private AuthRequest authRequest = auth_api.requestToken(
            "https://" + api_domain + "/api/v2/");
    private TokenHolder holder = execute_authRequest();
    private ManagementAPI mgmt = new ManagementAPI(
            api_domain,
            holder.getAccessToken());

    /**
     * FUNCION IMPORTANTE:-------------------------------------------------------
     * Lo que sucede es que uno de los valores de las propiedades se obtiene
     * ejecutando
     * un codigo "authRequest.execute()" el cual debe estar en un try catch, por eso
     * se crea esta funcion
     */
    private TokenHolder execute_authRequest() {
        TokenHolder holder = null;
        try {
            holder = authRequest.execute();
        } catch (APIException e) {
            throw new My_api_exception(
                    e.getMessage(),
                    e.getClass().getName(),
                    e.getDescription());
        } catch (Auth0Exception e) {
            throw new My_auth0_exception(
                    e.getMessage(),
                    e.getClass().getName());
        }
        return holder;
    }

    // CONSTRUCTOR----------------------------------------------------------------
    public Auth0() {
    } // Un constructor general para cuando no podamos usar @Autowired

    // CREAR UN USUARIO---------------------------------------------------------
    public CreatedUser create_a_user(
            String name,
            String email,
            String user_password,
            String phone_number) {
        CreatedUser auth0_user = null;

        SignUpRequest singup_request = auth_api.signUp(
                email,
                user_password.toCharArray(), // Con String esta descontinuado
                connection_type);

        // Ejecutamos la accion
        try {
            auth0_user = singup_request.execute(); // Creamos el usuario
            // agregamos la data restante
            update_all(
                    auth0_user.getUserId(),
                    name,
                    null, // email
                    null // phone_number esto me genera un "Cannot update phone_number for non-sms user"
            );

        } catch (APIException e) {
            throw new My_api_exception(
                    e.getMessage(),
                    e.getClass().getName(),
                    e.getDescription());
        } catch (Auth0Exception e) {
            throw new My_auth0_exception(
                    e.getMessage(),
                    e.getClass().getName());
        }
        return auth0_user;
    }

    // VALIDAR TOKEN------------------------------------------------------------
    /**
     * Para validar el token auth0 me dice que se debe usar una libreria (ver el
     * xml)
     * y ella funciona de esta forma; todo se podria decir que es una configuarion
     * para
     * obtener la secrey key con la cual se firman los token y poder validar
     */
    public boolean valid_token(String token) {
        try {
            // Configuramos los valores necesarios para que la libreria obtenga la key
            JwkProvider provider = new JwkProviderBuilder(
                    "https://" + api_domain).build();
            SignatureVerifier sigVerifier = SignatureVerifier.forRS256(
                    new PublicKeyProvider() {
                        @Override
                        public RSAPublicKey getPublicKeyById(String keyId) // obtenemos la key
                                throws PublicKeyProviderException {
                            try {
                                return (RSAPublicKey) provider.get(keyId).getPublicKey();
                            } catch (JwkException jwke) {
                                throw new PublicKeyProviderException(
                                        "Error obtaining public key", jwke); // Si no se obtuvo
                            }
                        }
                    });

            // Configuramos el valor necesario para que auth0 valide
            IdTokenVerifier idTokenVerifier = IdTokenVerifier.init(
                    "https://" + api_domain + "/",
                    client_id, // Cliend id
                    sigVerifier).build();

            // Hacemos la validacion del token donde si el token es invalido genera un error
            idTokenVerifier.verify(
                    token,
                    null // "NONCE" Es como un string que nosotros esperamos que tenga el token pero aun
                         // no funcionaba)
            // entocnes con null le decimos que no esperamos ningun "nonce"
            );

        } catch (IdTokenValidationException e) {
            // Este seria un error a la hora de validar mas no que esl token este mal
            throw new My_auth0_exception(
                    e.getMessage(),
                    e.getClass().getName());
        }
        return true;
    }

    // ACTUALIZAR CONTRASEÑA----------------------------------------------------
    public boolean update_password(String auth0_id, String new_password) {
        User new_data = new User();
        new_data.setPassword(
                new_password.toCharArray() // con String esta descontinuado
        );

        Request<User> request = mgmt.users().update(
                "auth0|" + auth0_id, new_data);

        // Ejecutamos la accion
        try {
            request.execute();
            return true;
        } catch (APIException e) {
            throw new My_api_exception(
                    e.getMessage(),
                    e.getClass().getName(),
                    e.getDescription());
        } catch (Auth0Exception e) {
            throw new My_auth0_exception(
                    e.getMessage(),
                    e.getClass().getName());
        }
    }

    // ACTUALIZA LAS PROPIEDADES (Menos la contraseña)
    public Void update_all(String auth0_id, String name, String email, String phone_number) {
        User new_data = new User();
        String valid_auth0_id = "auth0|" + auth0_id;

        if (name != null)
            new_data.setName(name);
        if (email != null)
            new_data.setEmail(email);
        if (phone_number != null)
            new_data.setPhoneNumber(phone_number);

        Request<User> request = mgmt.users().update(
                valid_auth0_id,
                new_data);

        // Ejecutamos la accion
        try {
            request.execute();
        } catch (APIException e) {
            throw new My_api_exception(
                    e.getMessage(),
                    e.getClass().getName(),
                    e.getDescription());
        } catch (Auth0Exception e) {
            throw new My_auth0_exception(
                    e.getMessage(),
                    e.getClass().getName());
        }

        return null;
    }
}

/**
 * //HACER LOGIN--------------------------------------------------------------
 * public TokenHolder login(String email, String password){
 * TokenHolder holder = null;
 * 
 * try {
 * AuthRequest request = auth_api.login(
 * email,
 * password.toCharArray(), //Con String esta descontinuado
 * "Username-Password-Authentication"
 * ).setAudience("https://dev-51lqhc63.us.auth0.com/api/v2/")
 * .setScope("openid profile email");
 * 
 * holder = request.execute();
 * 
 * } catch (APIException e) {
 * throw new RuntimeException(e.getDescription());
 * } catch (Auth0Exception e) {
 * throw new RuntimeException(e.getMessage());
 * }
 * 
 * return holder;
 * }
 */