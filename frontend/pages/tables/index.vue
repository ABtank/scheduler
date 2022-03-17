<template>
  <div>
    <md-tabs>
      <md-tab id="tab-users" md-label="Users">
        <TableUsers/>
      </md-tab>
      <md-tab id="tab-roles" md-label="Roles">
        <TableRoles/>
      </md-tab>
      <md-tab id="tab-disciplines" md-label="Disciplines">
        <TableDisciplines/>
      </md-tab>
      <md-tab id="tab-exercises" md-label="Exercises">
        <TableExercises/>
      </md-tab>
      <md-tab id="tab-lessons" md-label="Lessons">
        <TableLessons/>
      </md-tab>
    </md-tabs>
  </div>
</template>

<script>

import TableUsers from "../../components/TableUsers";
import TableRoles from "../../components/TableRoles";
import TableDisciplines from "../../components/TableDisciplines";
import TableExercises from "../../components/TableExercises";
import TableLessons from "../../components/TableLessons";

export default {
  name: "tables",
  components: {TableLessons, TableExercises, TableDisciplines, TableRoles, TableUsers}
}
</script>

<style scoped>

</style>
