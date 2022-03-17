<template>
  <div>
    <md-table v-model="searched" md-sort="email" md-sort-order="asc" md-card md-fixed-header>
      <md-table-toolbar>
        <div class="md-toolbar-section-start">
          <h1 class="md-title">Users</h1>
        </div>

<!--        <md-field md-clearable class="md-toolbar-section-end">-->
<!--          <md-input placeholder="Search by name..." v-model="search" @input="searchOnTable" />-->
<!--        </md-field>-->
      </md-table-toolbar>

      <md-table-empty-state
        md-label="No users found"
        :md-description="`No user found for this '${search}' query. Try a different search term or create a new user.`">
      </md-table-empty-state>

      <md-table-row slot="md-table-row" slot-scope="{ item }">
        <md-table-cell md-label="ID" md-sort-by="id" md-numeric>{{ item.id }}</md-table-cell>
        <md-table-cell md-label="Email" md-sort-by="email">{{ item.email }}</md-table-cell>
        <md-table-cell md-label="ФИО" md-sort-by="firstName">
          <a href="#" @click.prevent="openUser(item)">{{ item.firstName }} {{ item.middleName }} {{ item.lastName }}</a></md-table-cell>
        <md-table-cell md-label="phone" md-sort-by="phone">{{ item.phone }}</md-table-cell>
        <md-table-cell md-label="roles" >{{ item.roles }}</md-table-cell>
        <md-table-cell md-label="create/modify" md-sort-by="createDate">{{ item.createDate }}</br> {{ item.modifyDate }}</md-table-cell>
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
      }
    ]

  }),
  async asyncData({$axios}){
    const users = await $axios.$get('users')
    return {users}
  },
  async mounted() {
    this.searched = await this.$axios.$get('users')
  },
  methods: {
    searchOnTable () {
      this.searched = searchByName(this.users, this.search)
    },
    openUser(user) {
      this.$router.push(`/users/${user.id}`)
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
