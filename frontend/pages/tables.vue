<template>
<div>
  <md-tabs>
    <md-tab id="tab-users" md-label="Users" >
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
import TableLessons from "../components/TableLessons";
import TableDisciplines from "../components/TableDisciplines";
import TableRoles from "../components/TableRoles";
import TableUsers from "../components/TableUsers";
import TableExercises from "../components/TableExercises";
export default {
  name: "tables",
  components: {TableExercises, TableUsers, TableRoles, TableDisciplines, TableLessons}
}
</script>

<style scoped>

</style>
