package io.puppylpg.pokemon.pikachu;

import io.puppylpg.autoconfig.pikachu.PikachuAutoConfiguration;
import io.puppylpg.pokemon.pikachu.entity.Pikachu;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.autoconfigure.logging.ConditionEvaluationReportLoggingListener;
import org.springframework.boot.logging.LogLevel;
import org.springframework.boot.test.context.FilteredClassLoader;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.assertj.core.api.Assertions.assertThat;

public class PikachuAutoConfigurationTest {

    private final ApplicationContextRunner contextRunner = new ApplicationContextRunner()
            .withInitializer(new ConditionEvaluationReportLoggingListener(LogLevel.INFO))
            .withConfiguration(AutoConfigurations.of(PikachuAutoConfiguration.class))
            .withPropertyValues(
                    "pokemon.pikachu.enabled:true",
                    "pokemon.pikachu.name:pipi",
                    "pokemon.pikachu.height:3",
                    "pokemon.pikachu.girl-friend.name:right-hands",
                    "pokemon.pikachu.girl-friend.interest:play"
            );

    @Test
    public void testDefaultAutoConfig() {
        this.contextRunner.run(
                context -> {
                    assertThat(context).hasSingleBean(Pikachu.class);
                    assertThat(context).getBean("pikachu").isSameAs(context.getBean(Pikachu.class));
                    assertThat(context.getBean(Pikachu.class).getName()).isEqualTo("pipi");
                }
        );
    }

    @Test
    public void testWithoutEnabled() {
        // without enabled=true
        new ApplicationContextRunner()
                .withConfiguration(AutoConfigurations.of(PikachuAutoConfiguration.class)).run(
                        context -> {
                            assertThat(context).doesNotHaveBean(Pikachu.class);
                        }
                );
    }

    @Test
    public void defaultBeanBackOff() {
        this.contextRunner.withUserConfiguration(UserConfiguration.class).run(
                context -> {
                    assertThat(context).hasSingleBean(Pikachu.class);
                    assertThat(context).hasBean("developerDefined");
                }
        );

        // same as above
        this.contextRunner.withBean("developerDefined", Pikachu.class, "", 1).run(
                context -> {
                    assertThat(context).hasSingleBean(Pikachu.class);
                    assertThat(context).hasBean("developerDefined");
                }
        );
    }

    @Test
    public void withoutLib() {
        // a class loader to exclude lib in classpath
        this.contextRunner.withClassLoader(new FilteredClassLoader(Pikachu.class)).run(
                context -> {
                    assertThat(context).doesNotHaveBean(Pikachu.class);
                }
        );
    }

    @Configuration
    static class UserConfiguration {

        @Bean
        Pikachu developerDefined() {
            return new Pikachu("", 1);
        }
    }
}
