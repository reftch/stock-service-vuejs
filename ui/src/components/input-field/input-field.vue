<template>
  <div class="input-field">
    <input
      type="text"
      v-focus
      :value="getValue"
      placeholder="Select the Company..."
      @keyup="keyUp"
    />
    <div v-show="showOptions" class="input-field-options">
        <div v-for="item in options" :key="item.symbol" 
          class="input-field-item"
          @click="selectItem(item)">
            <span class="input-field-symbol">{{ item.symbol }}</span> ({{item.name}})
        </div>
    </div>

  </div>
</template>

<script lang="ts">
import { CompanyState } from "@/model";
import { computed, defineComponent, ref } from "vue";
import { useStore } from "vuex";

export default defineComponent({
  name: "InputField",
  model: {
    prop: "modelValue",
  },
  props: {
    modelValue: { default: "" },
    // options: Array,
  },
  setup(props, context) {
    const store = useStore();
    const showOptions = ref(false);
    const isTimeout = ref(false);
    const options = computed(() => store.getters["companies/getItems"]);

    // const options = [
    //   { symbol: 'AA', name: 'Alcoa Corp'},
    //   { symbol: 'AAA', name: 'AAF First Priority CLO Bond ETF'},
    //   { symbol: 'AA2.FRK', name: 'Amada Co.'},
    //   { symbol: 'AA4.FRK', name: 'Falck Renewables S.p.A'},
    //   { symbol: 'AA4.LON', name: 'Amedeo Air Four Plus Limited'},
    //   { symbol: 'AA9.FRK', name: 'Alfa Laval AB (publ)'},
    //   { symbol: 'AAAAX', name: 'DWS RREEF REAL ASSETS FUND CLASS A ssssssssssddddddddddddd'},
    //   { symbol: 'AAACX', name: ''},
    //   { symbol: 'AAADX', name: 'ALPINE RISING DIVIDEND FUND CLASS A'},
    //   { symbol: 'AAPL', name: 'Apple Inc.'},
    // ];

    // eslint-disable-next-line 
    const keyUp = async (event: any) => {
      context.emit('update:modelValue', event.target.value);

      showOptions.value = event.target.value !== '';
      if (event.key === 'Escape' || event.key === 'Enter') {
        showOptions.value = false;
        store.dispatch(`companies/reset`);
      } 

      if (showOptions.value) {
        if (!isTimeout.value) {
          isTimeout.value = true;
          setTimeout(() => {
            if (event.target.value !== '') {
              store.dispatch(`companies/fetch`, event.target.value);
            }
            isTimeout.value = false;
          }, 200);
        }
      }
    }

    const selectItem = (item: CompanyState) => {
      context.emit('update:modelValue', item.symbol);
      showOptions.value = false;
      store.dispatch(`companies/setItem`, item);
      store.dispatch(`companies/reset`);
    }

    return {
      showOptions,
      options,
      keyUp,
      selectItem,
      getValue: computed(() => props.modelValue),
    }
  }
});
</script>
