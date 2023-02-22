#include <stdio.h>
#include <stdlib.h>
#include <fcntl.h>
#include <unistd.h>
#include <signal.h>
#include <sys/wait.h>

void gestion_padre() {
  printf("Gestion Padre pid = %d\n", getpid());
}

void gestion_hijo() {
  printf("Gestion Hijo, pid = %d - pidPadre = %d\n", getpid(), getppid());
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
      signal (SIGUSR1, gestion_hijo);
      pause();
      break;
    default:
      sleep(1);
      kill(pid_hijo1, SIGUSR1);
      pid_hijo2 = fork();
      switch (pid_hijo2) {
        case -1:
          gestion_error();
          break;
        case 0:
          signal(SIGUSR1, gestion_hijo);
          pause();
          break;
        default:
          sleep(1);
          kill(pid_hijo2, SIGUSR1);
          pid_hijo1 = wait(NULL);
          pid_hijo2 = wait(NULL);
          gestion_padre();
      }
  }

}
