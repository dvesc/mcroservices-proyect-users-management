package example.mcroservice.users.utils.phone_validator;

import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;

public class Phone_validator {
  private PhoneNumberUtil phone_number_util;
  public PhoneNumber phone = new PhoneNumber(); //es public para poder acceder a sus metodos con "."

  
  
  //CONSTRUCTOR----------------------------------------------------------------
  public Phone_validator(String number, String code) {
    this.phone_number_util = PhoneNumberUtil.getInstance();
    
    //le asignamos las propiedades necesarias  (El num y el country_code)
    phone.setCountryCode(Integer.parseInt(code)) //el "+" se quita solo al transformarlo a int
      .setNationalNumber(Long.parseLong(number));
  }


  //METODOS--------------------------------------------------------------------
  public boolean validate_a_phone() {
    return phone_number_util.isValidNumberForRegion(
      phone, //el obj 
      phone_number_util.getRegionCodeForNumber(phone) //region ej "VE"
    );
  }
  
}
