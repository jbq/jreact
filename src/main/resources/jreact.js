var config = {
  staticMarkup: @@staticMarkup@@,
  harmony: @@harmony@@,
  sourceMaps: @@sourceMaps@@
};

var React = require('react');
var ReactDOMServer = require('react-dom/server')

var Component = require('@@mainComponentPath@@').default;
var props = @@props@@;

if (!config.staticMarkup) {
  ReactDOMServer.renderToString(React.createElement(Component, props))
} else {
  ReactDOMServer.renderToStaticMarkup(React.createElement(Component, props))
}
