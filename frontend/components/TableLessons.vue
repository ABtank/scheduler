<template>
  <div>
    <md-table v-model="searched" md-sort="name" md-sort-order="asc" md-card md-fixed-header>
      <md-table-toolbar>
        <div class="md-toolbar-section-start">
          <h1 class="md-title">Lessons</h1>
        </div>

<!--        <md-field md-clearable class="md-toolbar-section-end">-->
<!--          <md-input placeholder="Search by name..." v-model="search" @input="searchOnTable" />-->
<!--        </md-field>-->
      </md-table-toolbar>

      <md-table-empty-state
        md-label="No Lessons found"
        :md-description="`No Lesson found for this '${search}' query. Try a different search.`">
      </md-table-empty-state>

      <md-table-row slot="md-table-row" slot-scope="{ item }">
        <md-table-cell md-label="ID" md-sort-by="id" md-numeric>{{ item.id }}</md-table-cell>
        <md-table-cell md-label="Exercise" md-sort-by="exercise">{{ item.exercise }}</md-table-cell>
        <md-table-cell md-label="lesson" md-sort-by="lesson">{{ item.lesson }}</md-table-cell>
        <md-table-cell md-label="link" md-sort-by="link"><a href=""> {{ item.link }}</a></md-table-cell>
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
    filter.push.apply(filter,items.filter(item => toLower(item.exercise).includes(toLower(term))))
    filter.push.apply(filter,items.filter(item => toLower(item.lesson).includes(toLower(term))))
    filter.push.apply(filter,items.filter(item => toLower(item.link).includes(toLower(term))))
     return [...new Set(filter)]
  }

  return items
}

export default {
  name: 'TableLessons',
  data: () => ({
    search: null,
    searched: [],
    lessons: [
      {
        "id": 1,
        "lesson": "История",
        "link": "no",
        "exerciseId": 1,
        "exercise": "Урок истории",
        "dtStart": "2022-02-13T06:15:00Z"
      },
      {
        "id": 2,
        "lesson": "Физика",
        "link": "no",
        "exerciseId": 2,
        "exercise": "Урок физики",
        "dtStart": "2022-02-13T07:15:00Z"
      }
    ]
  }),
  async mounted() {
    this.searched = await this.$axios.$get('lessons')
  },
  methods: {
    searchOnTable () {
      this.searched = searchByName(this.lessons, this.search)
    }
  },
  created () {
    this.searched = this.lessons
  }
}
</script>

<style lang="scss" scoped>
.md-field {
  max-width: 300px;
}
</style>
