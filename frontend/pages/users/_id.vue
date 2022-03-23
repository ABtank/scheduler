<template>
  <div>
    <md-tabs class="md-transparent" md-alignment="fixed">
      <md-tab id="tab-prfile" md-label="Профиль" md-icon="account_box">
        <md-list class="md-double-line">
          <md-subheader>ФИО</md-subheader>
          <md-list-item>
            <md-icon class="md-primary">account_circle</md-icon>
            <div class="md-list-item-text">
              <span>{{ user.lastName }} {{ user.firstName }} {{ user.middleName }}</span>
              <span>ФИО</span>
            </div>
          </md-list-item>

          <md-divider></md-divider>

          <md-subheader>Phone</md-subheader>
          <md-list-item>
            <md-icon class="md-primary">phone</md-icon>
            <div class="md-list-item-text">
              <span>{{ user.phone }}</span>
              <span>Mobile</span>
            </div>
          </md-list-item>

          <md-subheader>Email</md-subheader>
          <md-list-item>
            <md-icon class="md-primary">email</md-icon>
            <div class="md-list-item-text">
              <span>{{ user.email }}</span>
              <span>E-mail</span>
            </div>
          </md-list-item>

          <md-subheader>Roles</md-subheader>
          <md-list-item v-for="role in user.descriptionRoles">
            <md-icon class="md-primary">roles</md-icon>
            <div class="md-list-item-text">
              <span>{{ role }}</span>
              <span>role</span>
            </div>
          </md-list-item>

        </md-list>
      </md-tab>

      <md-tab id="tab-teacher" md-label="Teacher" md-icon="record_voice_over">
        <md-list class="md-double-line">
          <md-subheader>Курсы/Предметы</md-subheader>
          <md-list-item md-expand v-for="exercise in exercises" v-if="exercise.teacherId === user.id">
            <md-icon>notifications_active</md-icon>
            <span class="md-list-item-text">{{exercise.name}}</span>

            <md-list slot="md-expand">
              <md-list-item class="md-inset" v-for="lesson in lessons" v-if="lesson.exerciseId === exercise.id">
                <p>{{lesson.lesson}}</p> <p>{{lesson.dtStart}}</p>
              </md-list-item>
            </md-list>
          </md-list-item>

        </md-list>
      </md-tab>

      <md-tab id="tab-student" md-label="Student" md-icon="spatial_tracking">
          <md-table v-model="lessonsStudents" md-sort="name" md-sort-order="asc" md-card>
            <md-table-toolbar>
              <h1 class="md-title">Все лекции</h1>
            </md-table-toolbar>

            <md-table-row slot="md-table-row" slot-scope="{ item }">
              <md-table-cell md-label="ID" md-numeric>{{ item.id }}</md-table-cell>
              <md-table-cell md-label="Курс" md-sort-by="email">{{ item.exerciseTitle }}</md-table-cell>
              <md-table-cell md-label="Лекция" md-sort-by="lessonTitle">{{ item.lessonTitle }}</md-table-cell>
              <md-table-cell md-label="Подтв. присутствие" md-sort-by="isAccepted">{{ item.isAccepted }}</md-table-cell>
              <md-table-cell md-label="Был на паре" md-sort-by="isAttend">{{ item.isAttend }}</md-table-cell>
              <md-table-cell md-label="Время начала" md-sort-by="lessonDtStart">{{ item.lessonDtStart }}</md-table-cell>
              <md-table-cell md-label="ссылка" md-sort-by="lessonLink"><a :href="item.lessonLink"> {{ item.lessonLink }}</a></md-table-cell>
            </md-table-row>
          </md-table>
      </md-tab>
    </md-tabs>
  </div>
</template>

<script>
export default {
  data: () => ({
    user: {},
    exercises: [{
      "discipline": "string",
      "disciplineId": 0,
      "dtModify": "2022-03-22T00:27:48.191Z",
      "duration": 45,
      "id": 0,
      "isPersonal": true,
      "name": "Физика ч.1",
      "quantity": 30,
      "teacher": "string",
      "teacherId": 0
    }],
    lessons: [
      {
        "dtStart": "string",
        "exercise": "string",
        "exerciseId": 0,
        "id": 0,
        "lesson": "Урок физики 1",
        "link": "string"
      }
    ],
    lessonsStudents:[
      {
        "dtCreate": "2022-03-22T00:31:23.599Z",
        "dtModify": "2022-03-22T00:31:23.599Z",
        "exerciseId": 0,
        "exerciseTitle": "string",
        "id": 0,
        "isAccepted": true,
        "isAttend": true,
        "lessonDtStart": "2022-03-22T00:31:23.599Z",
        "lessonId": 0,
        "lessonLink": "string",
        "lessonTitle": "string",
        "studentFirstName": "string",
        "studentId": 0,
        "studentLastName": "string",
        "studentMiddleName": "string"
      }
    ]
  }),
  async asyncData({$axios,params}) {
    const user = await $axios.$get(`users/${params.id}`)
    const exercises = await $axios.$get(`exercises`)
    const lessons = await $axios.$get(`lessons`)
    const lessonsStudents = await $axios.$get(`lessonsStudents/${params.id}`)
    return {
      user,exercises,lessons,lessonsStudents
    }
  },

  $_veeValidate({params}) {
    return /^\d+$/.test(params.id)
  },
}
</script>

<style scoped>

</style>
