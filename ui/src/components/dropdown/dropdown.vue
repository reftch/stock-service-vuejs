<template>
  <div
    class="dropdown"
   
    tabIndex="0"
    @blur="deactivate"
    @keydown="keydown"
    @click="activate"
  >
    <div ref="select" class="dropdown-select"  :class="{ 'dropdown-disabled': disabled }">
      <div class="dropdown-select-trigger">
        <span ref="model">{{ getValue }}</span>
        <div class="dropdown-arrow open"></div>
      </div>
      <div v-if="!disabled" class="dropdown-options">
        <span
          v-for="value of options"
          :key="value"
          :data-value="value"
          class="dropdown-option"
          :class="{ selected: getValue === value }"
          @click.stop="setNewValue(value); deactivate();"
        >
          {{ value }}
        </span>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent, computed, ref } from "vue";

export default defineComponent({
  model: {
    prop: "modelValue",
  },
  props: {
    modelValue: { default: "" },
    disabled: Boolean,
    width: Number,
    options: { type: Array },
  },
  setup(props, context) {
    const select = ref<HTMLElement>();
    const model = ref(null);
    const origin = ref(props.modelValue || '');

    const activate = () => {
      if (!props.disabled) {
        select.value?.classList.toggle("open");
      }
    };

    const deactivate = () => {
      select.value?.classList.remove("open");
    };

    const setNewValue = (value: string) => {
      context.emit("update:modelValue", value);
      context.emit("change", value);
    };

    const keydown = (event: KeyboardEvent) => {
      if (event.key === "Escape") {
        setNewValue(origin.value);
        deactivate();
      } else if (event.key === "Enter") {
        origin.value = props.modelValue;
        activate();       
      } else if (event.key === "ArrowDown" && select.value?.classList.contains("open")) {
        if (props.options) {
          for (let i = 0; i < props.options.length; i++) {
            const option = props.options[i];
            if (option === props.modelValue) {
              const nextValue = i + 1 === props.options.length ? props.options[0] : props.options[i + 1];
              setNewValue(String(nextValue));
              return;
            }
          }
        }
      } else if (event.key === "ArrowUp" && select.value?.classList.contains("open")) {
        if (props.options) {
          for (let i = 0; i < props.options.length; i++) {
            const option = props.options[i];
            if (option === props.modelValue) {
              const nextValue = i - 1 === -1 ? props.options[props.options.length - 1] : props.options[i - 1];
              setNewValue(String(nextValue));
              return;
            }
          }
        }
      }
    };

    return {
      activate,
      deactivate,
      select,
      model,
      keydown,
      setNewValue,
      getValue: computed(() => props.modelValue),
    };
  },
});
</script>
