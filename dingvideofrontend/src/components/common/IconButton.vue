<template>
  <div class="icon-button-wrapper">
   <LikeButton
   :is-initially-liked="isInitiallyLiked"
    :icon-src="iconSrc"
    :icon-tgt="iconTgt"
    :color-src="colorSrc"
    :color-tgt="colorTgt"
    @judgeLogin="judgeLogin"
    @like="toggleLike"
   ></LikeButton>
   <div class="bottom-number">
    {{ count }}
   </div>
  </div>
</template>
<script setup lang="ts">
const LikeButton = defineAsyncComponent(() => import("@/components/common/LikeButton.vue"))
import { ref } from 'vue';


const props = defineProps({
  iconSrc: {
    type: [String, Object],
    required: true,
  },
  iconTgt: {
    type: [String, Object],
    required: true,
  },
  colorSrc: {
    type: [String, Object],
    required: true,
  },
  colorTgt: {
    type: [String, Object],
    required: true,
  },
  isInitiallyLiked: {
    type: Boolean,
    default: false,
  },
  count: {
    type: [Number, Object],
  },
})
const emit = defineEmits(['like', 'judgeLogin'])

function judgeLogin(payload:Boolean) {
  emit('judgeLogin', payload)
}

function toggleLike(payload:Boolean) {
    emit('like', payload);
}
</script>

<style lang="scss" scoped>
.icon-button-wrapper{

  .bottom-number {
    position: relative;
    top: -5px;
    color: v-bind(colorSrc);
    font-size: 30px;
    font-weight: bold;
    cursor: pointer;
  }
}

</style>