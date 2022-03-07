import Vue from 'vue';
import ru from 'vee-validate/dist/locale/ru';
import VeeValidate, { Validator } from 'vee-validate';
Vue.use(VeeValidate, {
  errorBagName: 'veeErrors',
  fieldsBagName: 'veeFields'
});

Validator.localize('ru', ru);
Validator.localize('ru');
console.log('sdfsdf');
