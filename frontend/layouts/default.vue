<template>
  <div class="page-container">
    <md-app md-mode="fixed">
      <md-app-toolbar class="md-primary">
        <div class="md-toolbar-section-start">
          <md-button class="md-icon-button" v-if="$auth.loggedIn && !menuVisible" @click="toggleMenu">
            <md-icon>menu</md-icon>
          </md-button>
          <span class="md-title">SCHEDULER</span>
        </div>
        <div class="md-toolbar-section-end" v-if="$auth.loggedIn">
          <span>{{ userFio }}</span>
        </div>
      </md-app-toolbar>

      <md-app-drawer v-if="$auth.loggedIn" md-persistent="mini" :md-active.sync="menuVisible">
        <md-toolbar class="md-transparent" md-elevation="0">
          <span>Меню</span>
          <div class="md-toolbar-section-end">
            <md-button class="md-icon-button md-dense" @click="toggleMenu">
              <md-icon>keyboard_arrow_left</md-icon>
            </md-button>
          </div>
        </md-toolbar>

        <md-list>
          <NuxtLink
          v-for="item in menu"
          :key="item.link"
          tag="md-list-item"
          :to="item.link">
            <md-icon>{{ item.icon }}</md-icon>
            <span class="md-list-item-text">{{ item.text }}</span>
          </NuxtLink>
          <md-divider></md-divider>
          <md-list-item @click="logout">
            <md-icon>person_off</md-icon>
            <span class="md-list-item-text">Выход</span>
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
  components: {  },
  name: "default",
  data() {
    return {
      menuVisible: this.$auth.loggedIn
    };
  },
  computed: {
    menu() {
      let menuItems = [
        { text: 'Профиль', link: '/profile', icon: 'person' },
        { text: 'Дисциплина', link: '/disciplines', icon: 'dynamic_feed' },
        { text: 'Таблицы', link: '/tables', icon: 'source' },
        { text: 'Лекции', link: '/lessons', icon: 'record_voice_over' },
        { text: 'Пользователи', link: '/users', icon: 'groups' },
        { text: 'Расписание лекций', link: '/', icon: 'edit_calendar' },
      ];

      //TODO разделение по ролям
      return menuItems;
    },

    userFio() {
      if (this.$auth.loggedIn) {
        const user = this.$auth.user;
        return user.email ;
      }

      return '';
    }
  },
  methods: {
    toggleMenu() {
      this.menuVisible = !this.menuVisible
    },

    logout() {
      this.$auth.logout();
      window.location.href = "/sh";
    }
  }
}
</script>

<style scoped lang="scss">

</style>
