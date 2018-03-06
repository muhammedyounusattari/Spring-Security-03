package younus.attari.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@ComponentScan(basePackages={"younus.attari.config","younus.attari.controller"})
@EnableWebSecurity
@EnableWebMvc
@PropertySources({ @PropertySource("classpath:mymessages.properties") })
public class MainConfigurationClass extends WebSecurityConfigurerAdapter implements WebApplicationInitializer {

	
	@Bean
	public DataSource getDataSource(){
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://mysql.transcriptionhub.com/dbhybrid_transcription");
		dataSource.setUsername("hybrid_trans");
		dataSource.setPassword("trans#123");
		//dataSource.setSchema("dbhybrid_transcription");
		
		return dataSource;
	}
	
	
	@Bean
	public ViewResolver getBean() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}
	
	
	
//	@Override
//	public void configure(WebSecurity web) throws Exception {
//		// TODO Auto-generated method stub
//		web.ignoring().antMatchers("/resources/***");
//	}
//	

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests()
		.antMatchers("/proxy","/ui","/scripts","/webjars/**", "/data","/css").permitAll()
		.antMatchers("/login","/logout","/home","/").hasAnyRole("USER","ADMIN")
		.and().formLogin().loginPage("/login.jsp").loginProcessingUrl("/app")
//		.usernameParameter("username") //by default it will be added
//		.passwordParameter("password") //by default it will be added
		.defaultSuccessUrl("/home")
		.failureUrl("/login.jsp?error=1")
		.and().exceptionHandling().accessDeniedPage("/accessDenied")
		.and().logout().logoutUrl("/logoutUrl").logoutSuccessUrl("/login.jsp").
		and().csrf().disable();
		
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		//auth.inMemoryAuthentication().withUser("admin").password("admin").roles("ADMIN", "USER");
		auth.jdbcAuthentication().dataSource(this.getDataSource()).usersByUsernameQuery("select username,password,enabled from dbhybrid_transcription.users where username=?")
		.authoritiesByUsernameQuery("select username,role from dbhybrid_transcription.user_roles where username=?");
	}

	public void onStartup(ServletContext context) throws ServletException {

		AnnotationConfigWebApplicationContext webContext = new AnnotationConfigWebApplicationContext();
		webContext.setConfigLocation("younus.attari.config.MainConfigurationClass");

		ContextLoaderListener loadListener = new ContextLoaderListener(webContext);
		context.addListener(loadListener);
		
		ServletRegistration.Dynamic dispatcher = context.addServlet("Dispatcher", new DispatcherServlet(webContext));
		dispatcher.setLoadOnStartup(1);
		dispatcher.addMapping("/");
		
		

	}

}
