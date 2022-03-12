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

        <md-field :class="{'md-invalid': veeErrors.has('lastName')}">
          <label
          for="lastName"
          v-text="'Фамилия'" />

          <md-input
          name="lastName"
          id="lastName"
          v-model="profileData.lastName"
          v-validate="{ required: true }"
          :disabled="profileSending"
          data-vv-scope="profileForm"
          :data-vv-as="' '" />

          <span
          v-if="veeErrors.has('lastName')"
          v-text="getErrors('lastName', 'profileForm')"
          class="md-error" />
        </md-field>

        <md-field :class="{'md-invalid': veeErrors.has('firstName')}">
          <label
          for="firstName"
          v-text="'Имя'" />

          <md-input
          name="firstName"
          id="firstName"
          v-model="profileData.firstName"
          v-validate="{ required: true }"
          :disabled="profileSending"
          data-vv-scope="profileForm"
          :data-vv-as="' '" />

          <span
          v-if="veeErrors.has('firstName')"
          v-text="getErrors('firstName', 'profileForm')"
          class="md-error" />
        </md-field>

        <md-field :class="{'md-invalid': veeErrors.has('middleName')}">
          <label
          for="middleName"
          v-text="'Отчество'" />

          <md-input
          name="middleName"
          id="middleName"
          v-model="profileData.middleName"
          :disabled="profileSending"
          data-vv-scope="profileForm"
          :data-vv-as="' '" />

          <span
          v-if="veeErrors.has('middleName')"
          v-text="getErrors('middleName', 'profileForm')"
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
        v-text="'Сбосить'"
        class="profile__button" />
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
        <md-field :class="{'md-invalid': veeErrors.has('oldPassword')}">
          <label
          for="oldPassword"
          v-text="'Старый пароль'" />

          <md-input
          name="oldPassword"
          id="oldPassword"
          v-model="dataPassword.oldPassword"
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

        <md-field :class="{'md-invalid': veeErrors.has('passwordConfirmation')}">
          <label
          for="passwordConfirmation"
          v-text="'Повторите пароль'" />

          <md-input
          name="passwordConfirmation"
          id="passwordConfirmation"
          v-model="dataPassword.passwordConfirmation"
          v-validate="{ required: true, password_confirmation: dataPassword.password }"
          :disabled="passwordSending"
          type="password"
          data-vv-scope="passwordForm"
          :data-vv-as="' '" />

          <span
          v-if="veeErrors.has('passwordConfirmation')"
          v-text="getErrors('passwordConfirmation', 'passwordForm')"
          class="md-error" />
        </md-field>

        <md-progress-bar
        md-mode="indeterminate"
        v-if="passwordSending" />

        <md-button
        type="submit"
        class="md-raised md-primary profile__button profile__button"
        :disabled="passwordSending"
        v-text="'Сохранить'" />

        <md-button
        :disabled="profileSending"
        v-text="'Сбосить'"
        class="profile__button" />
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

  <md-snackbar
    md-position="center"
    :md-duration="4000"
    :md-active.sync="isSuccess"
    md-persistent
    class="md-primary">
    <span v-text="successText" />
    <md-button
      @click="isSuccess = false"
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
        lastName: null,
        firstName: null,
        middleName: null,
        phone: null,
      },
      dataPassword: {
        oldPassword: null,
        password: null,
        passwordConfirmation: null,
      },
      value: null,
      profileSending: false,
      passwordSending: false,
      isError: false,
      errorText: null,
      isSuccess: false,
      successText: null,
    }
  },

  beforeMount() {
    this.init();
  },

  methods: {
    getErrors(field, scope) {
      return this.veeErrors.collect(field, scope).join('. ')
    },

    async init() {
      this.profileSending = true;
      try {
        const response = await this.$axios.$get('/users/' + this.$auth.user.id);
        this.defaultData = response;
        this.profileData = { ...this.profileData, ...response };
      }
      catch (e) {
        this.isError = true;
        this.errorText = 'Получить профиль не удалось';
      }
      finally {
        this.profileSending = false;
      }
    },

    async handleProfileSubmit() {
      if (!(await this.$validator.validateAll('profileForm'))) {
        return;
      }
      this.profileSending = true;
      try {
        const response = await this.$axios.$put('/users', this.profileData);
        this.defaultData = response;
        this.isSuccess = true;
        this.successText = 'Ваши данные изменены';
      }
      catch (e) {
        this.isError = true;
        this.errorText = 'Изменить профиль не удалось';
      }
      finally {
        this.profileSending = false;
      }
    },

    async handlePasswordSubmit() { //TODO
      if (!(await this.$validator.validateAll('passwordForm'))) {
        return;
      }
      this.passwordSending = true;
      try {
        const response = await this.$axios.$put('/auth/change_password', this.dataPassword);
        this.isSuccess = true;
        this.successText = 'Ваш пароль изменён';
      }
      catch (e) {
        this.isError = true;
        this.errorText = 'Изменить пароль не удалось';
      }
      finally {
        this.passwordSending = false;
      }
    }
  }
}
</script>

<style scoped lang="scss">
.profile {
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;

  > * + * {
    margin-top: 20px;
  }

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
