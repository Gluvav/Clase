#include <stdio.h>
#include <stdlib.h>
#include <fcntl.h>
#include <unistd.h>
#include <signal.h>

void gestion_padre() {
  printf("Gestion Padre\n");
}

void gestion_hijo1() {
  printf("Gestion Hijo1\n");
}

void gestion_hijo2(){
  printf("Gestion hijo2\n");
}

void gestion_error(){
  printf("Error al crear el hijo\n");
}

void main() {

  pid_t pid_padre, pid_hijo1, pid_hijo2;
  pid_padre = getpid();
  pid_hijo1 = fork();

  switch(pid_hijo1){
    case -1:
      printf("Error al crear el proceso hijo...\n");
      exit(-1);
    case 0:
      signal (SIGUSR1, gestion_hijo1);
      //espera a que el llegue la seálñ para hacer gestion_hijo1
      //sleep(1);
      kill (pid_padre, SIGUSR1);
      pause();
      break;
    default:
      signal(SIGUSR1, gestion_padre);
      //espera a que el llegue la seálñ para hacer gestion_padre
      for (size_t i = 0; i < 1; i++) {
        pause();
        //sleep(1);
        kill(pid_hijo1, SIGUSR1);
      }
  }

}
