module.exports = {
  preset: '@vue/cli-plugin-unit-jest',
  moduleFileExtensions: ['js', 'jsx', 'json', 'vue', 'ts', 'tsx'],
  transform: {
    '^.+\\.vue$': 'vue-jest',
  },
}
