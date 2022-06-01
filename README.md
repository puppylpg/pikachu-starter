[toc]

该工程是一个module聚合工程，**但不是这些module的parent**。每个module都是一个独立的子工程。

spring boot autoconfig机制：https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#using.auto-configuration

# pikachu-entity
定义Pokemon和Pikachu实体类。

**该工程在autoconfig包里务必要作为optional=true引入**。

# pikachu-spring-boot-autoconfig

- https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#features.developing-auto-configuration

NOTE：该工程引入的pikachu-entity依赖，应该设为optional=true，否则项目引入该autoconfig包就相当于引入了pikachu-entity，
那么@ConditionalOnClass一定为true。不能让pikachu-entity包传递。

> springboot官方的[spring-boot-autoconfigure](https://github.com/spring-projects/spring-boot/blob/v2.7.0/spring-boot-project/spring-boot-autoconfigure/build.gradle)里全是optional。

生成IDE属性配置提示`META-INF/spring-configuration-metadata.json`：
- https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#appendix.configuration-metadata.annotation-processor

`META-INF/spring-autoconfigure-metadata.properties`。If that file is present, it is used to eagerly filter auto-configurations that do not match, which will improve startup time.：
- https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#features.developing-auto-configuration.custom-starter.autoconfigure-module

**测试autoconfig**：https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#features.developing-auto-configuration.testing

spring.factories是spring boot的入口：**META-INF/spring.factories配置xxxAutoConfiguration类，激活它的context配置；它的`@EnableConfigurationProperties`激活它的properties配置类的context**。

# pikachu-spring-boot-starter
自动配置。只要引入该starter，在使用Pokemon时，
如果不手动new Pokemon的实现，就会自动搞出来一只pikachu。

1. pikachu-spring-boot-autoconfig；
2. **pikachu-entity**；
3. 加上一些其他依赖（提供 "启动" 某个特性所需的所有依赖项）。

总之，引用了starter，啥都不用再额外引入（除了配置properties），就能使用这个库了：In a nutshell, adding the starter should provide everything needed to start using that library

> 当然，如果把autoconfig和依赖一起扔在starter里也没问题，只不过拆开更符合spring boot传统。

> This separation in two modules is in no way necessary. If "acme" has several flavors, options or optional features, then it is better to separate the auto-configuration as you can clearly express the fact some features are optional. Besides, you have the ability to craft a starter that provides an opinion about those optional dependencies. At the same time, others can rely only on the autoconfigure module and craft their own starter with different opinions.

- https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#features.developing-auto-configuration.custom-starter

> The starter is really an empty jar. Its only purpose is to provide the necessary dependencies to work with the library. You can think of it as an opinionated view of what is required to get started.

比如[spring-boot-starter-data-elasticsearch](https://github.com/spring-projects/spring-boot/blob/v2.7.0/spring-boot-project/spring-boot-starters/spring-boot-starter-data-elasticsearch/build.gradle)依赖[spring-boot-autoconfigure](https://github.com/spring-projects/spring-boot/blob/v2.7.0/spring-boot-project/spring-boot-autoconfigure/build.gradle)里和elasticsearch相关的auto config类，
但是后者在[spring-boot-starter](https://github.com/spring-projects/spring-boot/blob/v2.7.0/spring-boot-project/spring-boot-starters/spring-boot-starter/build.gradle)中，所以spring官方的所有xxx-starter都包含spring-boot-starter这个基础的starter：
> Either way, your starter must reference the core Spring Boot starter (spring-boot-starter) directly or indirectly (there is no need to add it if your starter relies on another starter). If a project is created with only your custom starter, Spring Boot’s core features will be honoured by the presence of the core starter.

配置名称不要使用spring boot用了的（server，spring，management等），最好用自己开头的，比如以`pokemon.pikachu`开头：
- https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#features.developing-auto-configuration.custom-starter.configuration-keys

# pikachu-starter-demo
一个引入pikachu-spring-boot-starter的web示例工程。

只是声明了一个pokemon并@Autowire，并没有配置是哪个pokemon实现，直接就用了。

启动后，通过`curl localhost:8080/show`查看结果，发现是pikachu在show。

所以pikachu被自动配置了。

# pikachu-starter-demo2
**自定义了一个Pokemon，所以配置Pikachu也没用。@Autowired的还是自定义的Pikachu。**

# others
另外starter包从pikachu.pic文件中读文本，在IDE中可以用File，当文件被打成jar包之后，就不能从file读了：
```
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("pikachu.pic").getFile());
```
但是可以从InputStream读：
```
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream("pikachu.pic");
        try {
            pic = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
```
打成jar之后，file就不是file了。和操作系统从文件系统上读普通文件是不一样的。

参阅：
- https://stackoverflow.com/a/20389418/7676237
- https://stackoverflow.com/a/10144757/7676237
