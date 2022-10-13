//package com.yumyapps.fakeoperator;
//
//
//import com.yumyapps.fakeoperator.employee.Employee;
//import com.yumyapps.fakeoperator.employee.EmployeeRepository;
//import com.yumyapps.fakeoperator.employee.EmployeeService;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
//import org.springframework.boot.autoconfigure.cache.CacheAutoConfiguration;
//import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
//import org.springframework.boot.test.context.TestConfiguration;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.cache.CacheManager;
//import org.springframework.cache.annotation.CacheConfig;
//import org.springframework.cache.annotation.EnableCaching;
//import org.springframework.context.annotation.Import;
//import org.springframework.data.redis.connection.RedisServer;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//import javax.annotation.PostConstruct;
//import java.util.Optional;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.mockito.BDDMockito.given;
//import static org.mockito.Mockito.times;
//import static org.mockito.Mockito.verify;
//
//@Import({ CacheConfig.class, Employee.class})
//@ExtendWith(SpringExtension.class)
//@EnableCaching
//@ImportAutoConfiguration(classes = {
//        CacheAutoConfiguration.class,
//        RedisAutoConfiguration.class
//})
//public class EmployeeCachingIntegrationTestClass {
//
//
//    @MockBean
//    private EmployeeRepository employeeRepository;
//
//    @Autowired
//    private EmployeeService employeeService;
//
//    @Autowired
//    private CacheManager cacheManager;
//
//
//
//    @Test
//    void givenRedisCaching_whenFindItemById_thenItemReturnedFromCache() {
//       Employee employee = Employee.builder().id(1L).name("Jameel").build();
//
//       given(employeeRepository.findById(1L))
//                .willReturn(Optional.of(employee));
//
//        Employee itemCacheMiss = employeeService.findEmployeeById(1L);
//        Employee itemCacheHit = employeeService.findEmployeeById(1L);
//
//        assertThat(itemCacheMiss).isEqualTo(employee);
//        assertThat(itemCacheHit).isEqualTo(employee);
//
//        verify(employeeRepository, times(1)).findById(1L);
//        assertThat(employeeFromCache()).isEqualTo(employee);
//    }
//
//
//    private Object employeeFromCache() {
//        return cacheManager.getCache("employeeCache").get(1L).get();
//    }
//
//
//
//    @TestConfiguration
//    static class EmbeddedRedisConfiguration {
//
////        private final RedisServer redisServer;
////
////        public EmbeddedRedisConfiguration() {
////            this.redisServer = new RedisServer();
////        }
////
//////        @PostConstruct
////        public void startRedis() {
////            redisServer.start();
////        }
////
////        @PreDestroy
////        public void stopRedis() {
////            this.redisServer.stop();
////        }
////    }
//
//
//}
