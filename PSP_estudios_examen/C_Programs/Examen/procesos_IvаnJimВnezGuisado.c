#include <stdio.h>
#include <sys/stat.h>
#include <unistd.h>
#include <signal.h>
#include <sys/wait.h>
 int main()
 {
	 pid_t pid1,pid2;
    void manejadorSignal() {
        printf("--Senyal recibida - Soy un hijo cualquiera (pid=%d, hijo de %d)\n",getpid(),getppid());
    }

	 printf("Padre.pid= %d\n",getpid());
     pid1= fork();
     if(pid1== 0) { //hijo1
        signal(SIGUSR1, manejadorSignal);
        pause();
        printf("--Soy hijo1 y ACABO!\n");

     } else {
       sleep(1);
       kill(pid1,SIGUSR1);
       pid2= fork();
       if(pid2== 0) { //hijo2
         signal(SIGUSR1, manejadorSignal);
         pause();
         printf("--Soy hijo2 y ACABO!\n");
       } else {
         sleep(1);
         kill(pid2,SIGUSR1);
         pid1= wait(NULL);
         pid2= wait(NULL);
         printf("Ya ha terminado mi Hijo con pid= %d\n",pid1);
         printf("Ya ha terminado mi Hijo con pid= %d\n",pid2);
         printf("Soy el padre, ya no tengo hijos... Y ACABO!!!\n");
       }
     }

	 return 0;
 }
