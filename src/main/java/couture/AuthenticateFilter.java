package couture;
import couture.AuthDB;

import javax.ws.rs.NotAuthorizedException;
import java.io.IOException;
import java.util.List;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.*;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

@Authenticator
public class AuthenticateFilter implements ContainerRequestFilter {

	private static final String PARAM_API_KEY = "apiKey";
	private static final String PARAM_TOKEN = "token";
	private static final long SECONDS_IN_MILLISECOND = 1000L;
	private static final int TTL_SECONDS = 60;

	@Override
	public void filter(ContainerRequestContext context) throws IOException {
        String authorizationHeader= context.getHeaderString(HttpHeaders.AUTHORIZATION);
        if (authorizationHeader == null || !authorizationHeader.startsWith("Apikey")) {
            throw new NotAuthorizedException("Authorization header must be provided");
        }
        // Extract the token
        String token1 = authorizationHeader.substring("Apikey".length()).trim();

                if (!authenticate(token1)) {
                    context.abortWith(responseUnauthorized());
                }
            
        
	}

//    }
    private Response responseUnauthorized() {
        return Response.status(Response.Status.UNAUTHORIZED)
            .type(MediaType.TEXT_PLAIN_TYPE)
            .entity("Unauthorized")
            .build();
    }
    private boolean authenticate(String apiKey) {
        final String secretKey = AuthDB.getSecretKey(apiKey);
        System.out.println(apiKey);
        // System.out.println(token);
        // No need to calculate digest in case of wrong apiKey
        if (StringUtils.isEmpty(secretKey)) {
            return false;
        }
        return true;


        // return false;
    }
}
