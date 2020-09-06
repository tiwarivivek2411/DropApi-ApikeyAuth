package couture;
import couture.Brand;
import couture.BrandRepository;

import couture.Authenticator;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Optional;
import io.dropwizard.auth.Auth;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
@Authenticator
@Path("/brands")
@Produces(MediaType.APPLICATION_JSON)
public class BrandResource {
	private final int defaultSize;
	private final BrandRepository brandRepository;
 
    public BrandResource(int defaultSize, BrandRepository brandRepository) {
        this.defaultSize = defaultSize;
        this.brandRepository = brandRepository;
    }

 	@PermitAll
    @GET
    public List<Brand> getBrands(@QueryParam("size") Optional<Integer> size) {
        return brandRepository.findAll(size.orElse(defaultSize));
    }

    @RolesAllowed({ "ADMIN" })
    @GET
    @Path("/id")
    public List<Brand> getBr(@QueryParam("size") Optional<Integer> size) {
        return brandRepository.findAll(size.orElse(defaultSize));
    }

    @RolesAllowed({ "ADMIN" })
    @GET
    @Path("/{id}")
    public Brand getById(@PathParam("id") Long id) {
        return brandRepository
          .findById(id)
          .orElseThrow(RuntimeException::new);
    }
}
