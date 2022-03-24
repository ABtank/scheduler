import Vue from 'vue'
import VeeValidate, { Validator } from 'vee-validate';
import ru from 'vee-validate/dist/locale/ru';
import { is } from 'vee-validate/dist/rules.esm';

Vue.use(VeeValidate, {
  errorBagName: 'veeErrors',
  fieldsBagName: 'veeFields',
});

Validator.localize('ru', ru);
Validator.localize('ru');


const validators = {
  password_confirmation: {
    getMessage(field, args) {
      return 'Введенные пароли не совпадают';
    },

    validate(value, args) {
      return is.validate(value, args);
    }
  },
}

Object.entries(validators).forEach(([name, options]) =>
  Validator.extend(name, options)
);
