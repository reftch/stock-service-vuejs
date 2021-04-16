
/* eslint-disable */

import Header from './header';
import InputField from './input-field';

const components: any = {
  cHeader: Header,
  cInputField: InputField,
}

const useComponents = {
  install(Vue: any, options = {}) {
    console.log(`Loading Vue.js Components Library, v${process.env.VUE_APP_VERSION}`)
    Object.keys(components).forEach(key => {
      Vue.component(key, components[key]);
    });
  }
};

export default useComponents;



