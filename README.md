# redis-cache-demo
业务缓存逻辑：  


- 失效：应用程序先从cache取数据，没有得到，则从数据库中取数据，成功后，放到缓存中。  
- 命中：应用程序从cache中取数据，取到后返回。  
- 更新：先把数据存到数据库中，成功后，再让缓存更新。
- 删除：从数据库删除，成功后，再让缓存失效


# spring cache annotation
[spring doc about cache annotation](https://docs.spring.io/spring/docs/4.3.14.BUILD-SNAPSHOT/spring-framework-reference/htmlsingle/#cache-annotations-cacheable "spring cache annotation")

    @Cacheable
	从缓存中读取，若成功，使用缓存；失败，从数据库读取
	@CachePut
	用于更新缓存，前提是缓存中存在相同数据，否则无效
	@CacheEvict
	用于删除缓存
	@EnableCaching
	启用以上注解

#Redis 安装教程
[Linux之Redis安装](https://www.bysocket.com/?p=917 "Linux之Redis安装")

