<template>
<div class="profile">
  <md-card class="profile__card">
    <md-card-header>
      <div class="md-title">Профиль пользователя</div>
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

        <md-field :class="{'md-invalid': veeErrors.has('last_name')}">
          <label
          for="email"
          v-text="'Фамилия'" />

          <md-input
          name="last_name"
          id="last_name"
          v-model="data.last_name"
          v-validate="{ required: true }"
          :disabled="sending"
          :data-vv-as="' '" />

          <span
          v-if="veeErrors.has('last_name')"
          v-text="getErrors('last_name')"
          class="md-error" />
        </md-field>

        <md-field :class="{'md-invalid': veeErrors.has('first_name')}">
          <label
          for="first_name"
          v-text="'Имя'" />

          <md-input
          name="first_name"
          id="first_name"
          v-model="data.first_name"
          v-validate="{ required: true }"
          :disabled="sending"
          :data-vv-as="' '" />

          <span
          v-if="veeErrors.has('first_name')"
          v-text="getErrors('first_name')"
          class="md-error" />
        </md-field>

        <md-field :class="{'md-invalid': veeErrors.has('middle_name')}">
          <label
          for="middle_name"
          v-text="'Отчество'" />

          <md-input
          name="middle_name"
          id="middle_name"
          v-model="data.middle_name"
          :disabled="sending"
          :data-vv-as="' '" />

          <span
          v-if="veeErrors.has('middle_name')"
          v-text="getErrors('middle_name')"
          class="md-error" />
        </md-field>

        <md-field :class="{'md-invalid': veeErrors.has('phone')}">
          <label
          for="phone"
          v-text="'Телефон'" />

          <md-input
          name="phone"
          id="phone"
          v-model="data.phone"
          :disabled="sending"
          :data-vv-as="' '" />

          <span
          v-if="veeErrors.has('phone')"
          v-text="getErrors('phone')"
          class="md-error" />
        </md-field>

        <md-progress-bar
        md-mode="indeterminate"
        v-if="sending" />

        <md-button
        type="submit"
        class="md-raised md-primary profile__button"
        :disabled="sending"
        v-text="'Зарегистрироваться'" />
      </form>
    </md-card-content>
  </md-card>

  <md-card class="profile__card">
    <md-card-header>
      <div class="md-title">Изменение пароля</div>
    </md-card-header>
    <md-card-content>
      <md-field :class="{'md-invalid': veeErrors.has('old_password')}">
        <label
          for="old_password"
          v-text="'Старый пароль'" />

        <md-input
          name="old_password"
          id="old_password"
          v-model="dataPassword.old_password"
          v-validate="{ required: true }"
          :disabled="sending"
          type="old_password"
          data-vv-scope="passwordForm"
          :data-vv-as="' '" />

        <span
          v-if="veeErrors.has('password')"
          v-text="getErrors('password')"
          class="md-error" />
      </md-field>

      <md-field :class="{'md-invalid': veeErrors.has('password')}">
        <label
          for="password"
          v-text="'Новый пароль'" />

        <md-input
          name="password"
          id="password"
          v-model="dataPassword.password"
          v-validate="{ required: true }"
          :disabled="sending"
          type="password"
          data-vv-scope="passwordForm"
          :data-vv-as="' '" />

        <span
          v-if="veeErrors.has('password')"
          v-text="getErrors('password')"
          class="md-error" />
      </md-field>

      <md-field :class="{'md-invalid': veeErrors.has('password_confirmation')}">
        <label
          for="password_confirmation"
          v-text="'Повторите пароль'" />

        <md-input
          name="password_confirmation"
          id="password_confirmation"
          v-model="dataPassword.password_confirmation"
          v-validate="{ required: true, password_confirmation: dataPassword.password }"
          :disabled="sending"
          type="password"
          data-vv-scope="passwordForm"
          :data-vv-as="' '" />

        <span
          v-if="veeErrors.has('password_confirmation')"
          v-text="getErrors('password_confirmation')"
          class="md-error" />
      </md-field>
    </md-card-content>
  </md-card>

  <md-snackbar
  md-position="center"
  :md-duration="4000"
  :md-active.sync="isProfileError"
  md-persistent
  class="md-error">
    <span v-text="'Зарегистрироватсья не удалось'" />
    <md-button
    @click="isProfileError = false"
    v-text="'Хорошо'"
    class="md-primary" />
  </md-snackbar>
</div>
</template>

<script>
export default {
  name: "Profile",
  data() {
    return {
      data: {
        email: null,
        last_name: null,
        first_name: null,
        middle_name: null,
        phone: null,
        user_role: 'ROLE_STUDENT',

      },
      dataPassword: {
        old_password: null,
        password: null,
        password_confirmation: null,
      },
      value: null,
      sending: false,
      isProfileError: false,
    }
  },
  methods: {
    getErrors(field) {
      return this.veeErrors.collect(field).join('. ')
    },

    async handleSubmit() {
      this.isProfileError = false;
      if (!(await this.$validator.validateAll())) {
        return;
      }
      this.sending = true;
      const response = await this.$axios.$post('/profile', this.data);
      this.sending = true;
      debugger;
      if (response.user) {
        let response = await this.$auth.loginWith('local', { data: { email: this.data.email, password: this.data.password } });
        window.location.href = '/';
      }
      else {
        this.isRegistrationError = true;
      }
      this.sending = false;
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
    max-width: 450px;
  }

  &__field-label {
    color: #00000089;
  }

  &__button {
    padding: 0 10px;
  }
}
</style>
