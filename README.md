[toc]

该工程是一个module聚合工程，但不是这些module的parent。每个module都是一个独立的子工程。

# pikachu-spring-boot-starter
一个pikachu的自动配置spring boot starter，只要引入该starter，在使用Pokemon时，
如果不手动new Pokemon的实现，就会自动搞出来一只pikachu。

该工程可以再分离一下，搞一个单独的pikachu包，完成Pokemon的实现类pikachu的功能，
然后starter工程引入该包，starter工程只做starter的那些auto config的事情。
不过鉴于两个包的内容都不多，干脆合成一个了。

# pikachu-starter-demo
一个引入pikachu-spring-boot-starter的web示例工程。

只是声明了一个pokemon并@Autowire，并没有配置是哪个pokemon实现，直接就用了。

启动后，通过`curl localhost:8080/show`查看结果，发现是pikachu在show。

所以pikachu被自动配置了。

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
