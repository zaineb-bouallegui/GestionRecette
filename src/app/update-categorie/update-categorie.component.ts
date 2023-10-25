import { Component, OnInit } from '@angular/core';
import { Categorie } from '../class/categorie';
import { ActivatedRoute, Router } from '@angular/router';
import { CategorieService } from '../services/categorie.service';

@Component({
  selector: 'app-update-categorie',
  templateUrl: './update-categorie.component.html',
  styleUrls: ['./update-categorie.component.css']
})
export class UpdateCategorieComponent implements OnInit {

  id: number=0;
  categorie: Categorie = new Categorie();
  constructor(private categorieService:CategorieService,
    private route:ActivatedRoute,
    private router:Router) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];
    this.categorieService.getCategorieById(this.id).subscribe(data => {
      this.categorie = data;
    }, error => console.log(error));
  }

  onSubmit(){
    this.categorieService.updateCategorie(this.id, this.categorie).subscribe( data =>{
      this.goToCategorieList();
    }
    , error => console.log(error));
  }

  goToCategorieList(){
    this.router.navigate(['/categorie-list']);
  }

}
