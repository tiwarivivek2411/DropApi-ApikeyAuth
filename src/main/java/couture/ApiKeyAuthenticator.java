// package couture;
 
// import java.util.Optional;
// import couture.DropApiConfiguration;
// import couture.ApplicationUser;

// import io.dropwizard.auth.AuthenticationException;
// import io.dropwizard.auth.Authenticator;
// import io.dropwizard.auth.basic.BasicCredentials;
 
 
// public class ApiKeyAuthenticator implements Authenticator<String, ApplicationUser> {
//     @Override
//     private boolean authenticate(String apiKey, String token) {
//         final String secretKey = AuthDB.getSecretKey(apiKey);

//         // No need to calculate digest in case of wrong apiKey
//         if (StringUtils.isEmpty(secretKey)) {
//             return false;
//         }

//         final long nowSec = System.currentTimeMillis() / SECONDS_IN_MILLISECOND;
//         long startTime = nowSec - TTL_SECONDS;
//         long endTime = nowSec + TTL_SECONDS;
//         for (; startTime < endTime; startTime++) {
//             final String toHash = apiKey + secretKey + startTime;
//             final String sha1 = DigestUtils.sha256Hex(toHash);
//             if (sha1.equals(token)) {
//                 return true;
//             }
//         }

//         return false;
//     }
    
// }