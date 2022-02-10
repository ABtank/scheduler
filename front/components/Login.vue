<template>
<div class="login">
  <a-card title="Вход">
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
      <a-form-item label="Пароль">
        <a-input-password
        placeholder="Пароль"
        v-decorator="[
          'password',
          { rules: [{ required: true, message: 'Введите свой пароль' }] },
        ]"
        name="password"
        v-model="data.password"/>
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
  auth: false,
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
      let response = await this.$auth.loginWith('local', { data: this.data });
      console.log(response);
    }
  }
}
</script>

<style scoped lang="scss">
.login {
  display: flex;
  justify-content: center;
  align-items: center;
}
</style>
