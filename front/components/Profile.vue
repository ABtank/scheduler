<template>
<div class="profile">
  <md-card class="profile__card">
    <md-card-header>
      <div class="md-title">Профиль пользователя</div>
    </md-card-header>
    <md-card-content>
      <form
      class="md-layout"
      @submit.prevent="handleProfileSubmit">
        <md-field :class="{'md-invalid': veeErrors.has('email')}">
          <label
          for="email"
          v-text="'E-mail'" />

          <md-input
          name="email"
          id="email"
          v-model="profileData.email"
          v-validate="{ required: true, email: true }"
          :disabled="profileSending"
          data-vv-scope="profileForm"
          :data-vv-as="' '" />

          <span
          v-if="veeErrors.has('email')"
          v-text="getErrors('email', 'profileForm')"
          class="md-error" />
        </md-field>

        <md-field :class="{'md-invalid': veeErrors.has('last_name')}">
          <label
          for="email"
          v-text="'Фамилия'" />

          <md-input
          name="last_name"
          id="last_name"
          v-model="profileData.last_name"
          v-validate="{ required: true }"
          :disabled="profileSending"
          data-vv-scope="profileForm"
          :data-vv-as="' '" />

          <span
          v-if="veeErrors.has('last_name')"
          v-text="getErrors('last_name', 'profileForm')"
          class="md-error" />
        </md-field>

        <md-field :class="{'md-invalid': veeErrors.has('first_name')}">
          <label
          for="first_name"
          v-text="'Имя'" />

          <md-input
          name="first_name"
          id="first_name"
          v-model="profileData.first_name"
          v-validate="{ required: true }"
          :disabled="profileSending"
          data-vv-scope="profileForm"
          :data-vv-as="' '" />

          <span
          v-if="veeErrors.has('first_name')"
          v-text="getErrors('first_name', 'profileForm')"
          class="md-error" />
        </md-field>

        <md-field :class="{'md-invalid': veeErrors.has('middle_name')}">
          <label
          for="middle_name"
          v-text="'Отчество'" />

          <md-input
          name="middle_name"
          id="middle_name"
          v-model="profileData.middle_name"
          :disabled="profileSending"
          data-vv-scope="profileForm"
          :data-vv-as="' '" />

          <span
          v-if="veeErrors.has('middle_name')"
          v-text="getErrors('middle_name', 'profileForm')"
          class="md-error" />
        </md-field>

        <md-field :class="{'md-invalid': veeErrors.has('phone')}">
          <label
          for="phone"
          v-text="'Телефон'" />

          <md-input
          name="phone"
          id="phone"
          v-model="profileData.phone"
          :disabled="profileSending"
          data-vv-scope="profileForm"
          :data-vv-as="' '" />

          <span
          v-if="veeErrors.has('phone')"
          v-text="getErrors('phone', 'profileForm')"
          class="md-error" />
        </md-field>

        <md-progress-bar
        md-mode="indeterminate"
        v-if="profileSending" />

        <md-button
        type="submit"
        class="md-raised md-primary profile__button"
        :disabled="profileSending"
        v-text="'Сохранить'" />

        <md-button
        :disabled="profileSending"
        v-text="'Сбосить'" />
      </form>
    </md-card-content>
  </md-card>

  <md-card class="profile__card">
    <md-card-header>
      <div class="md-title">Изменение пароля</div>
    </md-card-header>
    <md-card-content>
      <form
        class="md-layout"
        @submit.prevent="handlePasswordSubmit">
        <md-field :class="{'md-invalid': veeErrors.has('old_password')}">
          <label
          for="old_password"
          v-text="'Старый пароль'" />

          <md-input
          name="old_password"
          id="old_password"
          v-model="dataPassword.old_password"
          v-validate="{ required: true }"
          :disabled="passwordSending"
          type="old_password"
          data-vv-scope="passwordForm"
          :data-vv-as="' '" />

          <span
          v-if="veeErrors.has('password')"
          v-text="getErrors('password', 'passwordForm')"
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
          :disabled="passwordSending"
          type="password"
          data-vv-scope="passwordForm"
          :data-vv-as="' '" />

          <span
          v-if="veeErrors.has('password')"
          v-text="getErrors('password', 'passwordForm')"
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
          :disabled="passwordSending"
          type="password"
          data-vv-scope="passwordForm"
          :data-vv-as="' '" />

          <span
          v-if="veeErrors.has('password_confirmation')"
          v-text="getErrors('password_confirmation', 'passwordForm')"
          class="md-error" />
        </md-field>

        <md-progress-bar
        md-mode="indeterminate"
        v-if="passwordSending" />

        <md-button
        type="submit"
        class="md-raised md-primary profile__button"
        :disabled="passwordSending"
        v-text="'Зарегистрироваться'" />

        <md-button
        :disabled="profileSending"
        v-text="'Сбосить'" />
      </form>
    </md-card-content>
  </md-card>

  <md-snackbar
  md-position="center"
  :md-duration="4000"
  :md-active.sync="isError"
  md-persistent
  class="md-error">
    <span v-text="errorText" />
    <md-button
    @click="isError = false"
    v-text="'ОК'"
    class="md-primary" />
  </md-snackbar>
</div>
</template>

<script>
export default {
  name: "Profile",
  data() {
    return {
      defaultData: {},
      profileData: {
        email: null,
        last_name: null,
        first_name: null,
        middle_name: null,
        phone: null,
      },
      dataPassword: {
        old_password: null,
        password: null,
        password_confirmation: null,
      },
      value: null,
      profileSending: false,
      passwordSending: false,
      isError: false,
      errorText: false,
    }
  },
  methods: {
    getErrors(field, scope) {
      return this.veeErrors.collect(field, scope).join('. ')
    },

    async handleProfileSubmit() {
      if (!(await this.$validator.validateAll('profile'))) {
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
