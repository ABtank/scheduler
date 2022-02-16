<template>
<div class="registration">
  <a-card title="Вход" class="registration__card">
    <a-form
    @submit="handleSubmit"
    :form="form">
      <a-form-item label="E-mail">
        <a-input
        placeholder="E-mail"
        name="email"
        v-decorator="[
          'email',
          { rules: [{ required: true, message: 'Введите свой e-mail' }] },
        ]"
        v-model="data.email"/>
      </a-form-item>

      <a-divider />

      <a-form-item label="Фамилия">
        <a-input
          placeholder="Фамилия"
          name="last_name"
          v-decorator="[
          'last_name',
          { rules: [{ required: true, message: 'Введите свою фамилию' }] },
          ]"/>
      </a-form-item>
      <a-form-item label="Имя">
        <a-input
          placeholder="Имя"
          name="first_name"
          v-decorator="[
          'first_name',
          { rules: [{ required: true, message: 'Введите своё имя' }] },
          ]"/>
      </a-form-item>
      <a-form-item label="Отчество">
        <a-input
          placeholder="Отчество"
          name="middle_name"
          v-decorator="[
          'middle_name',
          { rules: [] },
          ]"/>
      </a-form-item>
      <a-form-item label="Телефон">
        <a-input
          placeholder="Телефон"
          name="phone"
          v-decorator="[
          'phone',
          { rules: [] },
          ]"/>
      </a-form-item>

      <a-divider />

      <a-radio-group
      v-decorator="[
         'user_role',
         { rules: [{ required: true, message: 'Выберите свою роль' }] },
       ]"
      name="user_role">
        <a-radio-button
        value="student"
        v-text="'Студент'" />
        <a-radio-button
        value="teacher"
        v-text="'Преподаватель'" />
      </a-radio-group>

      <a-divider />

      <a-form-item label="Пароль">
        <a-input-password
        placeholder="Пароль"
        v-decorator="[
          'password',
          { rules: [{ required: true, message: 'Введите свой пароль' }] },
        ]"
        name="password"/>
      </a-form-item>
      <a-form-item label="Повторите пароль">
        <a-input-password
          placeholder="Повторите пароль"
          v-decorator="[
            'password_confirmation',
            { rules: [{ required: true, message: 'Введите свой пароль' }] },
          ]"
          name="password_confirmation"/>
      </a-form-item>
      <a-divider />
      <a-button
      type="primary"
      @click="handleSubmit"
      v-text="'Войти'" />
    </a-form>
  </a-card>
</div>
</template>

<script>
export default {
  name: "Login",
  //auth: false,
  data() {
    return {
      form: this.$form.createForm(this),
      data: {
        email: null,
        password: null,
      }
    }
  },
  methods: {
    handleSubmit(e) {
      this.form.validateFields((err, values) => {
          if (!err) {
            this.sendForm();
          }
      });
    },
    async sendForm() {
      const values = this.form.getFieldsValue();
      const response = await this.$axios.$post('/auth/registration', );
      if (response.status) {
        let response = await this.$auth.loginWith('local', { data: { email: values.email, password: values.password } });
        window.location.href = '/';
      }
    }
  }
}
</script>

<style scoped lang="scss">
.registration {
  display: flex;
  justify-content: center;
  align-items: center;

  &__card {
    min-width: 450px;
  }
}
</style>
