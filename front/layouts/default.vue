<template>
  <div>
    <Header />
    <div class="content">
      <Nuxt />
    </div>
  </div>
</template>

<script>
import Header from "~/components/Header";
export default {
  components: { Header },
  name: "default"
}
</script>

<style scoped lang="scss">
.content {
  padding: 10px 0;
}
</style>
