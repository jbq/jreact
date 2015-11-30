package de.matrixweb.jreact;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import static org.junit.Assert.*;

import static org.hamcrest.CoreMatchers.*;

@SuppressWarnings("javadoc")
public class JReactClasspathTest {

  private JReact setupReact011() {
    final JReact react = new JReact(true);
    react.addRequirePath("react-0.11");
    return react;
  }

  private JReact setupReact012() {
    final JReact react = new JReact(true);
    react.addRequirePath("react-0.12");
    return react;
  }

  @Test
  public void testRenderWithReact011() throws IOException {
    dynamicMarkup(setupReact011());
  }

  @Test
  public void testRenderStaticMarkupWithReact011() throws IOException {
    staticMarkup(setupReact011());

  }

  @Test
  public void testRenderWithReact012() throws IOException {
    dynamicMarkup(setupReact012());
  }

  @Test
  public void testRenderMultipleTimes() throws IOException {
    final JReact react = setupReact012();
    for (int i = 0; i < 10; i++) {
      dynamicMarkup(react);
    }
  }

  @Test
  public void testRenderWithHarmony() throws IOException {
    final JReact react = setupReact012();
    react.setHarmony(true);

    final Map<String, Object> props = new HashMap<>();
    props.put("text", "Hello World!");
    final String result = react.renderToString("./test-harmony.js", props);

    assertThat(result, startsWith("<div data-reactid=\""));
    assertThat(result, endsWith("\">Hello World!</div>"));
  }

  @Test
  public void testRenderWithSourceMaps() throws IOException {
    final JReact react = setupReact012();
    react.setSourceMaps(true);
    dynamicMarkup(react);
  }

  @Test
  public void testRenderStaticMarkupWithReact012() throws IOException {
    staticMarkup(setupReact012());
  }

  private void dynamicMarkup(final JReact react) throws IOException {
    final Map<String, Object> props = new HashMap<>();
    props.put("text", "Hello World!");
    final String result = react.renderToString("./test.js", props);

    assertThat(result, startsWith("<div data-reactid=\""));
    assertThat(result, endsWith("\">Hello World!</div>"));
  }

  private void staticMarkup(final JReact react) throws IOException {
    final Map<String, Object> props = new HashMap<>();
    props.put("text", "Hello World!");
    final String result = react.renderToStaticMarkup("./test.js", props);

    assertThat(result, is("<div>Hello World!</div>"));
  }

}
