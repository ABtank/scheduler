<template>
  <div>
    <md-list class="md-double-line">
      <md-subheader>ФИО</md-subheader>
      <md-list-item>
        <md-icon class="md-primary">account_circle</md-icon>
        <div class="md-list-item-text">
          <span>{{ user.firstName }} {{ user.middleName }} {{ user.lastName }}</span>
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
      <md-list-item>
        <md-icon class="md-primary">roles</md-icon>
        <div class="md-list-item-text">
          <span>{{ user.roles }}</span>
          <span>roles</span>
        </div>
      </md-list-item>

    </md-list>
  </div>
</template>

<script>
export default {
  data: () => ({
    user: {}
  }),
  $_veeValidate({params}){
    return /^\d+$/.test(params.id)
  },
  async mounted() {
    this.user = await this.$axios.$get(`users/${this.$route.params.id}`)
  },
}
</script>

<style scoped>

</style>
