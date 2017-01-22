package com.hqjg.config.mybatise;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CharacterEncodingFilter;

/**
 * {@link EnableAutoConfiguration Auto-Configuration} for Mybatis. Contributes a
 * {@link SqlSessionFactory} and a {@link SqlSessionTemplate}.
 *
 * If {@link org.mybatis.spring.annotation.MapperScan} is used, or a configuration file is
 * specified as a property, those will be considered, otherwise this auto-configuration
 * will attempt to register mappers based on the interface definitions in or under the
 * root auto-configuration package.
 *
 * @author Eddú Meléndez
 * @author Josh Long
 */
/*@Configuration
@ConditionalOnClass({ SqlSessionFactory.class, SqlSessionFactoryBean.class })
@EnableConfigurationProperties(MybatisProperties.class)
@AutoConfigureAfter(DataSourceAutoConfiguration.class)
@EnableTransactionManagement*/
public class MybatisAutoConfiguration {
    /*private static Log log = LogFactory.getLog(MybatisAutoConfiguration.class);


    @Autowired
    private MybatisProperties properties;

    @Autowired(required = false)
    private Interceptor[] interceptors;

    @Autowired
    private ResourceLoader resourceLoader = new DefaultResourceLoader();

    @PostConstruct
    public void checkConfigFileExists() {
        if (this.properties.isCheckConfigLocation()) {
            Resource resource = this.resourceLoader
                    .getResource(this.properties.getConfig());
            System.out.println("-----------------------");
            try {
                System.out.println(resource.getURL());
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("-----------------------");
            Assert.state(resource.exists(),
                    "Cannot find config location: " + resource
                            + " (please add config file or check your Mybatis "
                            + "configuration)");
        }
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }

    @Bean(name = "sqlSessionFactory")
    @ConditionalOnMissingBean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean factory = new SqlSessionFactoryBean();
        factory.setDataSource(dataSource);
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        factory.setMapperLocations(resolver.getResources("classpath*: mapperXml*//*.xml"));
        *//*System.out.println("---------------------");
        System.out.println("dataSource = " + dataSource.toString());
        System.out.println("config = " + this.properties.getConfig());
        System.out.println("mapperLocation = " + this.properties.getMapperLocations());
        System.out.println("---------------------");*//*
        if (StringUtils.hasText(this.properties.getConfig())) {
            factory.setConfigLocation(
                    this.resourceLoader.getResource(this.properties.getConfig()));
        } else {
            if (this.interceptors != null && this.interceptors.length > 0) {
                factory.setPlugins(this.interceptors);
            }
            factory.setTypeAliasesPackage(this.properties.getTypeAliasesPackage());
            factory.setTypeHandlersPackage(this.properties.getTypeHandlersPackage());
            factory.setMapperLocations(this.properties.getMapperLocations());
        }
        SqlSessionFactory sqlSession = factory.getObject();
        sqlSession.getConfiguration().addMapper(StudentMapper_fail.class);
        return sqlSession;
    }

    //提供SqlSeesion
    *//*@Bean
    public SqlSessionFactory sqlSessionFactoryBean() throws Exception {

        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource());

        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();

        sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath*: mapperXml*//**//*.xml"));

        return sqlSessionFactoryBean.getObject();
    }*//*

    @Bean
    @ConditionalOnMissingBean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory,
                this.properties.getExecutorType());
    }

    *//**
     * 分页插件
     *
     * @param dataSource
     * @return
     * @author SHANHY
     * @create  2016年2月18日
     *//*
    @Bean
    public PageHelper pageHelper(DataSource dataSource) {
        log.info("注册MyBatis分页插件PageHelper");
        PageHelper pageHelper = new PageHelper();
        Properties p = new Properties();
        p.setProperty("offsetAsPageNum", "true");
        p.setProperty("rowBoundsWithCount", "true");
        p.setProperty("reasonable", "true");
        pageHelper.setProperties(p);
        return pageHelper;
    }

    @Bean(name="writeDataSource", destroyMethod = "close", initMethod="init")
    @ConfigurationProperties(prefix="spring.datasource")
    public DataSource dataSource() {
        return new com.alibaba.druid.pool.DruidDataSource();
        // return new org.apache.tomcat.jdbc.pool.DataSource();
    }*/

}
