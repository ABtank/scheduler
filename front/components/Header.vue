<template>
  <a-page-header
    title="РАСПИСАНИЯ"
    @back="() => $router.back()"
    class="header"
  />
</template>

<script>
export default {
  name: "Header"
}
</script>

<style scoped lang="scss">
.header {
  border: 1px solid rgb(235, 237, 240);

}
</style>
