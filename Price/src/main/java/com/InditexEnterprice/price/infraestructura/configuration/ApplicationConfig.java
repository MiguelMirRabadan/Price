package com.InditexEnterprice.price.infraestructura.configuration;

import com.InditexEnterprice.price.appplication.services.PriceService;
import com.InditexEnterprice.price.appplication.services.ProductService;
import com.InditexEnterprice.price.appplication.usecases.price.CreatePriceImp;
import com.InditexEnterprice.price.appplication.usecases.price.DeletePriceImp;
import com.InditexEnterprice.price.appplication.usecases.price.RetrievePriceImp;
import com.InditexEnterprice.price.appplication.usecases.price.UpdatePriceImp;
import com.InditexEnterprice.price.appplication.usecases.product.CreateProductImp;
import com.InditexEnterprice.price.appplication.usecases.product.DeleteProductImp;
import com.InditexEnterprice.price.appplication.usecases.product.RetrieveProductImp;
import com.InditexEnterprice.price.appplication.usecases.product.UpdateProductImp;
import com.InditexEnterprice.price.domain.ports.out.PriceRepositoryPort;
import com.InditexEnterprice.price.domain.ports.out.ProductRepositoryPort;
import com.InditexEnterprice.price.infraestructura.repositories.JpaPriceRepositoryAdapter;
import com.InditexEnterprice.price.infraestructura.repositories.JpaProductRepositoryAdapter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.annotation.EnableRetry;

@Configuration
@EnableRetry
public class ApplicationConfig {

    /*
        Declaramos los Beans de los services aquí para que la aplicación esté más centralizada, sea más Felxible y
        para que podamos tener una mayor control de la configuración de los beans. Sí que con @Service se simplifica
        todo, pero la idea es tenerlo todo más estructurado.
     */
    @Bean
    public PriceService priceService(PriceRepositoryPort priceRepositoryPort){
        return new PriceService(new CreatePriceImp(priceRepositoryPort),
                                new UpdatePriceImp(priceRepositoryPort),
                                new DeletePriceImp(priceRepositoryPort),
                                new RetrievePriceImp(priceRepositoryPort));
    }

    @Bean
    public ProductService productService(ProductRepositoryPort productRepositoryPort){
        return new ProductService(new CreateProductImp(productRepositoryPort),
                new UpdateProductImp(productRepositoryPort),
                new RetrieveProductImp(productRepositoryPort),
                new DeleteProductImp(productRepositoryPort));
    }

    @Bean
    public ProductRepositoryPort productRepositoryPort(JpaProductRepositoryAdapter jpaProductRepositoryAdapter){
        return jpaProductRepositoryAdapter;
    }
    @Bean
    public PriceRepositoryPort priceRepositoryPort(JpaPriceRepositoryAdapter jpaPriceRepositoryAdapter) {
        return jpaPriceRepositoryAdapter;
    }

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

    /*

    Otra posibilidad, es crear uestro propio swagger, aquí he dejado comentado el código que sería necesario
    ,claramente, añadiendo las dependencias correspondientes en el pom y la anotacion de EnableSwagger2. Todo y
    que hay que tener en cuenta las versiones con las que estamos trabajando, ya que por el momento, para springboot 3
    me da problemas.

    @Bean
    public Docket apiDocket(){
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.basePackage("com.InditexEnterprice.price.infraestructura.controllers"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(getApiInfo());
    }
    private ApiInfo getApiInfo(){
        return new ApiInfoBuilder()
                .title("API Documentation")
                .description("API Description")
                .version("1.0")
                .build();
    }*/
}
