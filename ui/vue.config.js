module.exports = {
  publicPath: '/',
  productionSourceMap: false,
  devServer: {
    port: 8081,
    disableHostCheck: true,
    headers: {
      'Access-Control-Allow-Origin': 'http://localhost:8081',
      'Access-Control-Allow-Methods': 'HEAD, GET, POST, PUT, PATCH, DELETE',
      'Access-Control-Allow-Headers': 'Content-Type, Authorization',
      'Access-Control-Allow-Credentials': true,
    },
  },
};