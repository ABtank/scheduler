<template>
  <div>
    <md-table v-model="searched" md-sort="name" md-sort-order="asc" md-card md-fixed-header>
      <md-table-toolbar>
        <div class="md-toolbar-section-start">
          <h1 class="md-title">Exercises</h1>
        </div>

<!--        <md-field md-clearable class="md-toolbar-section-end">-->
<!--          <md-input placeholder="Search by name..." v-model="search" @input="searchOnTable" />-->
<!--        </md-field>-->
      </md-table-toolbar>

      <md-table-empty-state
        md-label="No Exercises found"
        :md-description="`No Exercise found for this '${search}' query. Try a different search.`">
      </md-table-empty-state>

      <md-table-row slot="md-table-row" slot-scope="{ item }">
        <md-table-cell md-label="ID" md-sort-by="id" md-numeric>{{ item.id }}</md-table-cell>
        <md-table-cell md-label="Exercise" md-sort-by="name">{{ item.name }}</md-table-cell>
        <md-table-cell md-label="isPersonal" md-sort-by="isPersonal">{{ item.isPersonal }}</md-table-cell>
        <md-table-cell md-label="duration" md-sort-by="duration">{{ item.duration }}</md-table-cell>
        <md-table-cell md-label="quantity" md-sort-by="quantity">{{ item.quantity }}</md-table-cell>
        <md-table-cell md-label="teacher" md-sort-by="teacher">{{ item.teacher }}</md-table-cell>
        <md-table-cell md-label="discipline" md-sort-by="discipline">{{ item.discipline }}</md-table-cell>
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
    filter.push.apply(filter,items.filter(item => toLower(item.duration).includes(toLower(term))))
    filter.push.apply(filter,items.filter(item => toLower(item.quantity).includes(toLower(term))))
    filter.push.apply(filter,items.filter(item => toLower(item.teacher).includes(toLower(term))))
    filter.push.apply(filter,items.filter(item => toLower(item.discipline).includes(toLower(term))))
     return [...new Set(filter)]
  }

  return items
}

export default {
  name: 'TableExercises',
  data: () => ({
    search: null,
    searched: [],
    exercises: [
      {
        "id": 1,
        "name": "Урок истории",
        "isPersonal": true,
        "duration": 45,
        "quantity": 1,
        "teacherId": 3,
        "teacher": "Сергей Сергеевич Новиков",
        "disciplineId": 9,
        "discipline": "История"
      },
      {
        "id": 2,
        "name": "Урок физики",
        "isPersonal": true,
        "duration": 45,
        "quantity": 1,
        "teacherId": 4,
        "teacher": "Виктор Петрович Сидоров",
        "disciplineId": 1,
        "discipline": "Физика"
      }
    ]
  }),
  async mounted() {
    this.searched = await this.$axios.$get('exercises')
  },
  methods: {
    searchOnTable () {
      this.searched = searchByName(this.exercises, this.search)
    }
  },
  created () {
    this.searched = this.exercises
  }
}
</script>

<style lang="scss" scoped>
.md-field {
  max-width: 300px;
}
</style>
