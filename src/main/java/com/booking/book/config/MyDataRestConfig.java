package com.booking.book.config;

import com.booking.book.entity.Orders;
import com.booking.book.entity.Room;
import com.booking.book.entity.RoomCategory;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.core.mapping.ExposureConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@Configuration
public class MyDataRestConfig implements RepositoryRestConfigurer {

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration restConf, CorsRegistry cors) {
        restConf.exposeIdsFor(Room.class, RoomCategory.class, Orders.class);
        ExposureConfiguration config = restConf.getExposureConfiguration();
        config.forDomainType(Orders.class).withItemExposure((metadata, httpMethods) ->
                httpMethods.disable(HttpMethod.POST));
    }
}
