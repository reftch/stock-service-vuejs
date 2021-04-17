
/* eslint-disable */

import Chart from './chart';
import Dropdown from './dropdown';
import Header from './header';
import InputField from './input-field';
import Table from './table';

const components: any = {
  cChart: Chart,
  cDropdown: Dropdown,
  cHeader: Header,
  cInputField: InputField,
  cTable: Table,
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



