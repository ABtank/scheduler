<template>
  <div>
    <md-table v-model="searched" md-sort="name" md-sort-order="asc" md-card md-fixed-header>
      <md-table-toolbar>
        <div class="md-toolbar-section-start">
          <h1 class="md-title">Roles</h1>
        </div>

        <md-field md-clearable class="md-toolbar-section-end">
          <md-input placeholder="Search by name..." v-model="search" @input="searchOnTable" />
        </md-field>
      </md-table-toolbar>

      <md-table-empty-state
        md-label="No Roles found"
        :md-description="`No Role found for this '${search}' query. Try a different search.`">
      </md-table-empty-state>

      <md-table-row slot="md-table-row" slot-scope="{ item }">
        <md-table-cell md-label="ID" md-sort-by="id" md-numeric>{{ item.id }}</md-table-cell>
        <md-table-cell md-label="Role" md-sort-by="name">{{ item.name }}</md-table-cell>
        <md-table-cell md-label="description" md-sort-by="description">{{ item.description }}</md-table-cell>
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
    filter.push.apply(filter,items.filter(item => toLower(item.name).includes(toLower(term))))
    filter.push.apply(filter,items.filter(item => toLower(item.description).includes(toLower(term))))
     return [...new Set(filter)]
  }

  return items
}

export default {
  name: 'TableRoles',
  data: () => ({
    search: null,
    searched: [],
    roles: [
      {
        "id": 1,
        "name": "ROLE_USER",
        "description": "Пользователь"
      },
      {
        "id": 2,
        "name": "ROLE_TEACHER",
        "description": "Преподаватель"
      },
      {
        "id": 3,
        "name": "ROLE_ADMIN",
        "description": "Администратор"
      },
      {
        "id": 4,
        "name": "ROLE_STUDENT",
        "description": "Ученик"
      }
    ]
  }),
  methods: {
    searchOnTable () {
      this.searched = searchByName(this.roles, this.search)
    }
  },
  created () {
    this.searched = this.roles
  }
}
</script>

<style lang="scss" scoped>
.md-field {
  max-width: 300px;
}
</style>
