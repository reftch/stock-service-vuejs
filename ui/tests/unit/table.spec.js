import { shallowMount } from '@vue/test-utils'
import Table from '@/components/table/table.vue'

describe('Tests table component', () => {

  it('Test default behavior', () => {
    const wrapper = shallowMount(Table, {
      props: {
        item: null
      }
    });

    let classes = wrapper.find('.chart-area').classes();
    expect(classes.length).toEqual(1)
    const table = wrapper.find('.table');
    classes = table.classes();
    expect(classes.length).toEqual(1)
    
    expect(wrapper.find('.table-heading').exists()).toBe(false)
  });

  it('Test table header', () => {
    const wrapper = shallowMount(Table, {
      props: {
        item: null,
        columns: [ { id: 'id' }, { id: 'name' }]
      }
    });

    let classes = wrapper.find('.chart-area').classes();
    expect(classes.length).toEqual(1)
    classes = wrapper.find('.table').classes();
    expect(classes.length).toEqual(1)

    expect(wrapper.find('.table-heading').exists()).toBe(true)
    expect(wrapper.find('.table-cell').exists()).toBe(true)
    expect(wrapper.find('.table-heading-title').exists()).toBe(true)
   
    expect(wrapper.findAll('.table-heading-title').length).toEqual(2)
    expect(wrapper.findAll('.table-heading-title')[0].text()).toEqual('id')
    expect(wrapper.findAll('.table-heading-title')[1].text()).toEqual('name')
    
  });

  it('Test object displayed', () => {
    const wrapper = shallowMount(Table, {
      props: {
        item: { id: 'test', name: 'Test Object'},
        columns: [ { id: 'id' }, { id: 'name' }]
      }
    });

    let classes = wrapper.find('.chart-area').classes();
    expect(classes.length).toEqual(1)
    classes = wrapper.find('.table').classes();
    expect(classes.length).toEqual(1)

    expect(wrapper.find('.table-heading').exists()).toBe(true)
    expect(wrapper.find('.table-cell').exists()).toBe(true)
    expect(wrapper.find('.table-heading-title').exists()).toBe(true)
   
    expect(wrapper.findAll('.table-heading-title').length).toEqual(2)
    expect(wrapper.findAll('.table-heading-title')[0].text()).toEqual('id')
    expect(wrapper.findAll('.table-heading-title')[1].text()).toEqual('name')
    
    expect(wrapper.findAll('.table-cell-parameter').length).toEqual(2)
    expect(wrapper.findAll('.table-cell-parameter')[0].text()).toEqual('id')
    expect(wrapper.findAll('.table-cell-parameter')[1].text()).toEqual('name')

    expect(wrapper.findAll('.table-cell-text').length).toEqual(2)
    expect(wrapper.findAll('.table-cell-text')[0].text()).toEqual('test')
    expect(wrapper.findAll('.table-cell-text')[1].text()).toEqual('Test Object')
  });

})
