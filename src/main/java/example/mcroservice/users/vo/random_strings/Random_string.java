package example.mcroservice.users.vo.random_strings;

import java.security.SecureRandom;

public class Random_string {
  private String random;

  public Random_string(int string_length) {
    this.random = generateRandomString(string_length);
  }

  // La encontre en internet XD
  private String generateRandomString(int length) {
    String CHAR_LOWER = "abcdefghijklmnopqrstuvwxyz";
    String CHAR_UPPER = CHAR_LOWER.toUpperCase();
    String NUMBER = "0123456789";

    String DATA_FOR_RANDOM_STRING = CHAR_LOWER + CHAR_UPPER + NUMBER;
    SecureRandom random = new SecureRandom();

    if (length < 1)
      throw new IllegalArgumentException();

    StringBuilder sb = new StringBuilder(length);

    for (int i = 0; i < length; i++) {
      // 0-62 (exclusive), retornos aleatorios 0-61
      int rndCharAt = random.nextInt(DATA_FOR_RANDOM_STRING.length());
      char rndChar = DATA_FOR_RANDOM_STRING.charAt(rndCharAt);

      sb.append(rndChar);
    }

    return sb.toString();
  }

  // GETTERS AND SETTERS--------------------------------------------------------
  public String getRandom_string() {
    return random;
  }

  public void setRandom_string(String random_string) {
    this.random = random_string;
  }
}
