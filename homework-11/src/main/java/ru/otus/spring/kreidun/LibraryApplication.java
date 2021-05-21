package ru.otus.spring.kreidun;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import ru.otus.spring.kreidun.repositories.AuthorRepository;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@SpringBootApplication
public class LibraryApplication {

	public static void main(String[] args) {

		ApplicationContext context = SpringApplication.run(LibraryApplication.class);

		AuthorRepository authorRepository = context.getBean(AuthorRepository.class);
		authorRepository.findAll().subscribe(p -> System.out.println(p.getLastName()));
	}

	@Bean
	public RouterFunction<ServerResponse> indexRouter(@Value("classpath:/templates/listBooks.html") final Resource indexHtml) {
		return route(GET("/"), request -> ok().contentType(MediaType.TEXT_HTML).bodyValue(indexHtml));
	}

	@Bean
	public RouterFunction<ServerResponse> indexRouterAuthor(@Value("classpath:/templates/listAuthors.html") final Resource indexHtml) {
		RouterFunction<ServerResponse> route = route()
				.GET("/authors", request -> ok().contentType(MediaType.TEXT_HTML).bodyValue(indexHtml))
				.build();
		return route;
	}

	@Bean
	public RouterFunction<ServerResponse> indexRouterGenre(@Value("classpath:/templates/listGenres.html") final Resource indexHtml) {
		return route(GET("/genres"), request -> ok().contentType(MediaType.TEXT_HTML).bodyValue(indexHtml));
	}
	@Bean
	public RouterFunction<ServerResponse> indexRouterComment(@Value("classpath:/templates/listComments.html") final Resource indexHtml) {
		return route(GET("/comments"), request -> ok().contentType(MediaType.TEXT_HTML).bodyValue(indexHtml));
	}
}
