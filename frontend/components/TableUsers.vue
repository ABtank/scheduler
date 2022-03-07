<template>
  <div>
    <md-table v-model="searched" md-sort="name" md-sort-order="asc" md-card md-fixed-header>
      <md-table-toolbar>
        <div class="md-toolbar-section-start">
          <h1 class="md-title">Users</h1>
        </div>

        <md-field md-clearable class="md-toolbar-section-end">
          <md-input placeholder="Search by name..." v-model="search" @input="searchOnTable" />
        </md-field>
      </md-table-toolbar>

      <md-table-empty-state
        md-label="No users found"
        :md-description="`No user found for this '${search}' query. Try a different search term or create a new user.`">
        <md-button class="md-primary md-raised" @click="newUser">Create New User</md-button>
      </md-table-empty-state>

      <md-table-row slot="md-table-row" slot-scope="{ item }">
        <md-table-cell md-label="ID" md-sort-by="id" md-numeric>{{ item.id }}</md-table-cell>
        <md-table-cell md-label="Email" md-sort-by="email">{{ item.email }}</md-table-cell>
        <md-table-cell md-label="ФИО" md-sort-by="firstName">{{ item.firstName }} {{ item.middleName }} {{ item.lastName }}</md-table-cell>
        <md-table-cell md-label="phone" md-sort-by="phone">{{ item.phone }}</md-table-cell>
        <md-table-cell md-label="roles" >{{ item.roles }}</md-table-cell>
        <md-table-cell md-label="createDate" md-sort-by="createDate">{{ item.createDate }}</md-table-cell>
        <md-table-cell md-label="modifyDate" md-sort-by="modifyDate">{{ item.modifyDate }}</md-table-cell>
      </md-table-row>
    </md-table>
  </div>
</template>

<script>
const toLower = text => {
  return text.toString().toLowerCase()
}

const searchByName = (items, term) => {
  if (term) {
    let filter =[]
    filter.push.apply(filter,items.filter(item => toLower(item.email).includes(toLower(term))))
    filter.push.apply(filter,items.filter(item => toLower(item.firstName).includes(toLower(term))))
    filter.push.apply(filter,items.filter(item => toLower(item.middleName).includes(toLower(term))))
    filter.push.apply(filter,items.filter(item => toLower(item.lastName).includes(toLower(term))))
    filter.push.apply(filter,items.filter(item => toLower(item.phone).includes(toLower(term))))
    return [...new Set(filter)]
  }

  return items
}

export default {
  name: 'TableUsers',
  data: () => ({
    search: null,
    searched: [],
    users: [
      {
        id: 1,
        email: "student12@mail.com",
        phone: "11-11-11",
        firstName: "иван",
        middleName: "иванович",
        lastName: "иванов",
        roles: [
          "ROLE_STUDENT"
        ],
        createDate: "2022-03-01T22:49:20.984+00:00",
        modifyDate: "2022-03-01T22:49:20.984+00:00",
      },
      {
        id: 2,
        email: "student1@mail.ru",
        phone: "9-111-111-11-11",
        firstName: "Иван",
        middleName: "Петрович",
        lastName: "Иванов",
        roles: [
          "ROLE_STUDENT"
        ],
        createDate: "2022-03-01T22:49:20.984+00:00",
        modifyDate: "2022-03-01T22:49:20.984+00:00",
      },
      {
        id: 3,
        email: "student2@mail.ru",
        phone: "9-222-111-11-11",
        firstName: "Сергей",
        middleName: "Сергеевич",
        lastName: "Новиков",
        roles: [
          "ROLE_STUDENT"
        ],
        createDate: "2022-03-01T22:49:20.984+00:00",
        modifyDate: "2022-03-01T22:49:20.984+00:00",
      },
      {
        id: 4,
        email: "teacher1@mail.ru",
        phone: "9-333-111-11-11",
        firstName: "Виктор",
        middleName: "Петрович",
        lastName: "Сидоров",
        roles: [
          "ROLE_TEACHER"
        ],
        createDate: "2022-03-01T22:49:20.984+00:00",
        modifyDate: "2022-03-01T22:49:20.984+00:00",
      },
      {
        id: 5,
        email: "teacher2@mail.ru",
        phone: "9-444-111-11-11",
        firstName: "Леонид",
        middleName: "Петрович",
        lastName: "Леонов",
        roles: [
          "ROLE_TEACHER"
        ],
        createDate: "2022-03-01T22:49:20.984+00:00",
        modifyDate: "2022-03-01T22:49:20.984+00:00",
      },
      {
        id: 6,
        email: "email@mail.com",
        phone: "+79500309968",
        firstName: "Иванин",
        middleName: "Ивано",
        lastName: "Иванинович",
        roles: [
          "ROLE_ADMIN",
          "ROLE_USER",
          "ROLE_TEACHER",
          "ROLE_STUDENT"
        ],
        createDate: "2022-03-02T23:28:55.703+00:00",
        modifyDate: "2022-03-03T00:12:19.294+00:00",
      }
    ]

  }),
  methods: {
    async newUser() {
      let $get2 = this.$axios.$get('/v1/users');
      let get = this.$axios.$get('/v1/auth/user');
      console.log(this.$auth.$state.user.email)
      console.log(get);
      try {
        let response = await this.$axios.$get('/v1/auth/user');
        console.log(`response`);
        console.log(response.data);
      } catch (e) {
        this.isLoginError = true;
      }
    },
    searchOnTable () {
      this.searched = searchByName(this.users, this.search)
    }
  },
  created () {
    this.searched = this.users
  }
}
</script>

<style lang="scss" scoped>
.md-field {
  max-width: 300px;
}
</style>
