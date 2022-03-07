<template>
  <div class="page-container">
    <md-app md-waterfall md-mode="fixed">
      <md-app-toolbar class="md-primary">
        <
        <md-button class="md-icon-button" @click="menuVisible = !menuVisible">
          <md-icon>menu</md-icon>
        </md-button>
        <NuxtLink to="/">
          <md-avatar class="md-avatar-icon md-small">
            <md-icon>home</md-icon>
          </md-avatar>
          <span class="md-title">SCHEDULER</span>
        </NuxtLink>
        <NuxtLink to="tables"><md-button class="md-primary md-raised">tables</md-button></NuxtLink>
        <NuxtLink to="login"><md-button class="md-primary md-raised">Login</md-button></NuxtLink>
        <md-button class="md-primary md-raised" @click="logout">Logout</md-button>
        <md-button class="md-primary md-raised" ><a target='_blank' href="../sh/swagger-ui/">OAPI</a></md-button>
<!--        <span v-model:accesskey="$auth">WELCOME user=#{{ $auth.user.id }} email={{ $auth.user.email }}</span>-->
      </md-app-toolbar>

      <md-app-drawer :md-active.sync="menuVisible">
        <md-toolbar class="md-transparent" md-elevation="0">Navigation</md-toolbar>

        <md-list>
          <md-list-item>
            <md-icon>home</md-icon>
            <span class="md-list-item-text"><a target='_blank' href="../sh/swagger-ui/">Scheduler Open API</a></span>
          </md-list-item>

          <md-list-item>
            <md-icon>person_add</md-icon>
            <span class="md-list-item-text"><NuxtLink to="/registration">Регистрация</NuxtLink></span>
          </md-list-item>

          <md-list-item>
            <md-icon>login</md-icon>
            <span class="md-list-item-text"><NuxtLink to="/login">Login</NuxtLink></span>
          </md-list-item>

          <md-list-item>
            <md-icon>person_off</md-icon>
            <span class="md-list-item-text"><NuxtLink to="/test">Logoff</NuxtLink></span>
          </md-list-item>

          <md-list-item>
            <md-icon>send</md-icon>
            <span class="md-list-item-text">Sent Mail</span>
          </md-list-item>

          <md-list-item>
            <md-icon>dynamic_feed</md-icon>
            <span class="md-list-item-text">Дисциплина</span>
          </md-list-item>

          <md-list-item>
            <md-icon>record_voice_over</md-icon>
            <span class="md-list-item-text">Лекции</span>
          </md-list-item>

          <md-list-item>
            <md-icon>groups</md-icon>
            <span class="md-list-item-text">Пользователи</span>
          </md-list-item>

          <md-list-item>
            <md-icon>edit_calendar</md-icon>
            <span class="md-list-item-text">Расписание лекций</span>
          </md-list-item>

          <md-list-item>
            <md-icon>safety_divider</md-icon>
            <span class="md-list-item-text">Роли</span>
          </md-list-item>

          <md-list-item>
            <md-icon>settings</md-icon>
            <span class="md-list-item-text">Settings</span>
          </md-list-item>
        </md-list>
      </md-app-drawer>

      <md-app-content>
        <Nuxt/>
      </md-app-content>
    </md-app>
  </div>
</template>


<style lang="scss" scoped>
.md-app {
  max-height: 100%;
  border: 1px solid rgba(#000, .12);
}

// Demo purposes only
.md-drawer {
  width: 230px;
  max-width: calc(100vw - 125px);
}
</style>
<script>
export default {
  name: 'Reveal',
  components: {},

  data: () => ({
    menuVisible: false
  }),
  methods: {
    logout() {
      this.$auth.logout();
      window.location = "/sh"
    }
  }
}
</script>
