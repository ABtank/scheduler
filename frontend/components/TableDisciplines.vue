<template>
  <div>
    <md-table v-model="searched" md-sort="name" md-sort-order="asc" md-card md-fixed-header>
      <md-table-toolbar>
        <div class="md-toolbar-section-start">
          <h1 class="md-title">Disciplines</h1>
        </div>

        <md-field md-clearable class="md-toolbar-section-end">
          <md-input placeholder="Search by name..." v-model="search" @input="searchOnTable" />
        </md-field>
      </md-table-toolbar>

      <md-table-empty-state
        md-label="No Disciplines found"
        :md-description="`No Discipline found for this '${search}' query. Try a different search.`">
      </md-table-empty-state>

      <md-table-row slot="md-table-row" slot-scope="{ item }">
        <md-table-cell md-label="ID" md-sort-by="id" md-numeric>{{ item.id }}</md-table-cell>
        <md-table-cell md-label="Discipline" md-sort-by="name">{{ item.name }}</md-table-cell>
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
     return [...new Set(filter)]
  }

  return items
}

export default {
  name: 'TableDisciplines',
  data: () => ({
    search: null,
    searched: [],
    disciplines: [
      {
        "id": 6,
        "name": "Английский язык"
      },
      {
        "id": 8,
        "name": "Информатика"
      },
      {
        "id": 9,
        "name": "История"
      },
      {
        "id": 5,
        "name": "Литература"
      },
      {
        "id": 4,
        "name": "Математика"
      },
      {
        "id": 7,
        "name": "Природоведение"
      },
      {
        "id": 3,
        "name": "Психология"
      },
      {
        "id": 1,
        "name": "Физика"
      },
      {
        "id": 2,
        "name": "Химия"
      }
    ]
  }),
  methods: {
    searchOnTable () {
      this.searched = searchByName(this.disciplines, this.search)
    }
  },
  created () {
    this.searched = this.disciplines
  }
}
</script>

<style lang="scss" scoped>
.md-field {
  max-width: 300px;
}
</style>
