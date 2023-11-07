<template>
  <div class="like-button" @click="toggleLike">
    <el-icon>
      <!-- <component
      :is="icon"
      :class="{'liked': isLiked, 'animate': isAnimating}"
    ></component> -->
    <iconify-icon 
    :icon="currentIcon"
    :class="{'liked': isLiked, 'animate': isAnimating}"
    >

    </iconify-icon>

    </el-icon>
  </div>
</template>

<script lang="ts" setup>
import 'iconify-icon';
import { ref } from 'vue';
import { loginStore } from '@/store/login/login.ts'


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
  }
})
const currentIcon = ref(props.iconSrc);  

// 登录状态
const store = loginStore()
const emit = defineEmits(['like', 'judgeLogin'])


const isLiked = ref(props.isInitiallyLiked);
const isAnimating = ref(false);

const toggleLike = () => {
  isLiked.value = !isLiked.value;
  isAnimating.value = true;
  if (currentIcon.value === props.iconSrc) {
    currentIcon.value = props.iconTgt
  } else {
    currentIcon.value = props.iconSrc
  }

  emit('judgeLogin', store.existToken());

  // Remove the animation class after it completes
  setTimeout(() => {
    isAnimating.value = false;
    // Emit an event for parent component if needed
    emit('like', isLiked.value);
  }, 600); // the timeout should match your CSS animation duration
};



</script>

<style scoped>
.like-button {
  cursor: pointer;
  display: inline-block;
}

.liked {
  color: v-bind(colorTgt); /* Change color to red when liked */
}
.el-icon {
  font-size: 45px;
  color: v-bind(colorSrc);
}
.el-icon:hover {
  animation: swing 1.5s ease;
}
.animate {
  animation: pop 0.6s ease;
}

@keyframes pop {
  0% { transform: scale(1); }
  50% { transform: scale(0.8); }
  100% { transform: scale(1); }
}
@keyframes swing {
  10% {
    transform: rotate(15deg);
  }
  20% {
    transform: rotate(-10deg);
  }
  30% {
    transform: rotate(5deg);
  }
  40% {
    transform: rotate(-5deg);
  }
  50%,
  100% {
    transform: rotate(0deg);
  }

}
</style>
