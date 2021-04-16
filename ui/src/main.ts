import { createApp } from 'vue'
import App from './App.vue'
import store from './store'
import focus from './directives/focus';
import useComponents from './components'

// createApp(App)
//   .use(store)
//   .use(useComponents)
//   .mount('#app')

const instance = createApp(App);

instance.directive('focus', focus);

instance
  .use(store)
  .use(useComponents)
  .mount('#app');
