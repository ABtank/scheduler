<template>
  <div class="page-container">
    <md-app md-waterfall md-mode="fixed">
      <md-app-toolbar class="md-primary">
        <<md-button class="md-icon-button" @click="menuVisible = !menuVisible">
        <md-icon>menu</md-icon>
      </md-button>
        <span class="md-title">SCHEDULER</span>
      </md-app-toolbar>

      <md-app-drawer :md-active.sync="menuVisible">
        <md-toolbar class="md-transparent" md-elevation="0">Navigation</md-toolbar>

        <md-list>
          <md-list-item>
            <md-icon>home</md-icon>
            <span class="md-list-item-text"><a target='_blank' href="http://localhost:8189/sh/swagger-ui/">Scheduler Open API</a></span>
          </md-list-item>

          <md-list-item>
            <md-icon>login</md-icon>
            <span class="md-list-item-text"><router-link to="/login">Login</router-link></span>
          </md-list-item>

          <md-list-item>
            <md-icon>person_add</md-icon>
            <span class="md-list-item-text"><router-link to="/registration">Регистрация</router-link></span>
          </md-list-item>

          <md-list-item>
            <md-icon>person_off</md-icon>
            <span class="md-list-item-text"><router-link to="/registration">Login of</router-link></span>
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
        <Nuxt />
      </md-app-content>
    </md-app>
  </div>
</template>



<script>
export default {
  name: 'Reveal',
  data: () => ({
    menuVisible: false
  })
}
</script>

<style scoped lang="scss">

</style>
