<template>
  <div class='app'>
    <FullCalendar :options='calendarOptions' />
  </div>
</template>

<script>
import FullCalendar from '@fullcalendar/vue'
import interactionPlugin from '@fullcalendar/interaction'
import timeGridPlugin from '@fullcalendar/timegrid'
export default {
  components: {
    FullCalendar
  },
  async asyncData({$axios,params}) {
    const lessons = await $axios.$get(`lessons`)
    const events=[]
    lessons.forEach(l => {
      events.push({ title: l.lesson, date: l.dtStart })
    })
    console.log(events)
    return {
    }
  },
  data() {
    return {
      calendarOptions: {
        plugins: [interactionPlugin, timeGridPlugin],
        initialView: 'timeGridWeek',
        nowIndicator: true,
        editable: true,
        initialEvents: [
          { title: 'nice event', start: new Date() }
        ],
        events: [
           {title: 'Урок История ч.2', date: '2022-03-25 09:15:00'},
     {title: 'Урок История ч.3', date: '2022-03-28 09:15'},
     {title: 'Урок История ч.4', date: '2022-03-29 09:15'},
     {title: 'Урок История ч.5', date: '2022-03-30 09:15'},
     {title: 'Урок История ч.6', date: '2022-03-31 09:15'},
     {title: 'Урок Физики ч.2', date: '2022-03-25 10:15'},
     {title: 'Урок Физики ч.3', date: '2022-03-28 10:15'},
     {title: 'Лекция Физики ч.1', date: '2022-03-24 10:15'},
     {title: 'Лекция Java Core', date: '2022-03-24 12:15'},
     {title: 'Лекция Java FX', date: '2022-03-25 12:15'},
     {title: 'Лекция Java Spring Core', date: '2022-03-28 12:15'},
     {title: 'Лекция Java Spring Security', date: '2022-03-29 12:15'},
     {title: 'Лекция Java Hibernate', date: '2022-03-30 12:15'},
     {title: 'Лекция Java Многопоточность', date: '2022-03-31 12:15'},
     {title: 'Урок Физики ч.4', date: '2022-04-01 10:15'},
     {title: 'Урок Физики ч.5', date: '2022-04-02 10:15'},
     {title: 'Урок Физики ч.6', date: '2022-04-03 10:15'},
        ]
      }
    }
  }
}
</script>

<style scoped>
.app {
  font-family: Arial, Helvetica Neue, Helvetica, sans-serif;
  font-size: 14px;
}
</style>
