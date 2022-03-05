<template>
<div class="login">
  <md-card class="login__content">
    <md-card-header>
      <div class="md-title">Вход</div>
    </md-card-header>
    <md-card-content>
      <form
      class="md-layout"
      @submit.prevent="handleSubmit">
        <md-field :class="{'md-invalid': veeErrors.has('email')}">
          <label
          for="email"
          v-text="'E-mail'" />

          <md-input
          name="email"
          id="email"
          v-model="data.email"
          v-validate="{ required: true, email: true }"
          :disabled="sending"
          :data-vv-as="' '" />

          <span
          v-if="veeErrors.has('email')"
          v-text="getErrors('email')"
          class="md-error" />
        </md-field>

        <md-field :class="{'md-invalid': veeErrors.has('password')}">
          <label
          for="password"
          v-text="'Пароль'" />

          <md-input
          name="password"
          id="password"
          v-model="data.password"
          v-validate="'required'"
          :disabled="sending"
          type="password"
          :data-vv-as="' '" />

          <span
          v-if="veeErrors.has('password')"
          v-text="getErrors('password')"
          class="md-error" />
        </md-field>

        <md-progress-bar
        md-mode="indeterminate"
        v-if="sending" />

        <md-button
        type="submit"
        class="md-raised md-primary"
        :disabled="sending"
        v-text="'Войти'" />
      </form>
    </md-card-content>
  </md-card>

  <md-snackbar
  md-position="center"
  :md-duration="4000"
  :md-active.sync="isLoginError"
  md-persistent
  class="md-error">
    <span v-text="'Логин или пароль не правильный'" />
    <md-button
    @click="isLoginError = false"
    v-text="'Хорошо'"
    class="md-primary" />
  </md-snackbar>
</div>
</template>

<script>
export default {
  name: "Login",
  auth: false,
  data() {
    return {
      data: {
        email: null,
        password: null,
      },
      isLoginError: false,
      sending: false,
    }
  },
  methods: {
    getErrors(field) {
      return this.veeErrors.collect(field).join('. ')
    },

    async handleSubmit(e) {
      this.isLoginError = false;
      if (!(await this.$validator.validateAll())) {
        return;
      }
      this.sending = true;
      try {
        let response = await this.$auth.loginWith('local', { data: this.data });
        window.location.href = data.basedir;
      }
      catch (e) {
        this.isLoginError = true;
      }
      this.sending = false;
    },
  }
}
</script>

<style scoped lang="scss">
.login {
  display: flex;
  justify-content: center;
  align-items: center;

  &__content {
    max-width: 300px;
  }
}
</style>
